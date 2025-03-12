package ru.itmo.blps.labs.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.CustomUserDetails;
import ru.itmo.blps.labs.domain.PhoneAuthRequest;
import ru.itmo.blps.labs.domain.RefreshStorage;
import ru.itmo.blps.labs.domain.User;
import ru.itmo.blps.labs.domain.dto.AuthRequest;
import ru.itmo.blps.labs.domain.dto.AuthResponse;
import ru.itmo.blps.labs.domain.dto.JwtEmailPassRequest;
import ru.itmo.blps.labs.domain.dto.JwtRequest;
import ru.itmo.blps.labs.domain.dto.JwtResponse;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.RefreshStorageRepository;
import ru.itmo.blps.labs.utils.PasswordHash;
import ru.itmo.blps.labs.utils.QrCodeGenerator;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PhoneAuthRequestService phoneAuthRequestService;
    private final RefreshStorageRepository refreshStorageRepository;
    private final JwtProvider jwtProvider;

    private final Map<String, String> authCodeStorage = new HashMap<>();

    private final SecureRandom secureRandom = new SecureRandom();

    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails user) {
            return user.getUser().getId();
        }
        return null;
    }

    public AuthResponse request(@NonNull AuthRequest authRequest) {
        String phoneNumber = authRequest.getPhone();
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new CustomException(ExceptionEnum.INCORRECT_PHONE_NUMBER);
        }

        Optional<User> userOptional = userService.findByPhone(phoneNumber);

        boolean register = userOptional.isEmpty();

        sendAuthCode(phoneNumber, register);

        return new AuthResponse(register);
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        String phoneRegex = "^7[\\d]{10}$";
        return phoneNumber != null && phoneNumber.matches(phoneRegex);
    }

    private void sendAuthCode(String phoneNumber, Boolean register) {

        String code = getRandomCode();
        requestSMS(phoneNumber, code);

        savePhoneAuthRequest(phoneNumber, register, code);
    }

    private @NotNull String getRandomCode() {
        int randomNumber = secureRandom.nextInt(9000) + 1000;
        return Integer.toString(randomNumber);
    }

    private void requestSMS(String phoneNumber, String code) {
        log.info("SMS with code {} to phone number {} was sent", code, phoneNumber);
    }

    private void savePhoneAuthRequest(String phoneNumber, Boolean register, String code) {
        PhoneAuthRequest phoneAuthRequest = new PhoneAuthRequest();
        phoneAuthRequest.setPhoneNumber(phoneNumber);
        phoneAuthRequest.setCode(code);
        phoneAuthRequest.setDatetime(Instant.now());
        phoneAuthRequest.setRegister(register);
        phoneAuthRequest.setAttempts(0);
        phoneAuthRequestService.save(phoneAuthRequest);

        authCodeStorage.put(phoneNumber, code);
    }

    public JwtResponse confirm(@NonNull JwtRequest jwtRequest) {
        String phoneNumber = jwtRequest.getPhone();
        String code = jwtRequest.getCode();
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new CustomException(ExceptionEnum.INCORRECT_PHONE_NUMBER);
        }

        saveAttempt(phoneNumber);

        var user = userService.findByPhone(phoneNumber);
        if (user.isEmpty()) {
            var newUser = new User();
            newUser.setPhone(phoneNumber);
            newUser.setName(jwtRequest.getName());
            userService.save(newUser);
        }

        return checkCodeAndGetJwtResponse(phoneNumber, code);
    }

    public JwtResponse confirmQr(Long userId) {
        var user = userService.getById(userId);

        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);

        saveRefreshToken(user.getId(), refreshToken);

        authCodeStorage.remove(user.getPhone());
        return new JwtResponse(accessToken, refreshToken, false);
    }

    private void saveAttempt(String phoneNumber) {
        Optional<PhoneAuthRequest> lastAttempt = phoneAuthRequestService.getLastByPhone(phoneNumber);
        if (lastAttempt.isPresent()) {
            PhoneAuthRequest tempLastAttempt = lastAttempt.get();
            var countAttempts = tempLastAttempt.getAttempts();
            if (countAttempts != null) {
                tempLastAttempt.setAttempts(countAttempts + 1);
                phoneAuthRequestService.save(tempLastAttempt);
            }
        }
    }

    private JwtResponse checkCodeAndGetJwtResponse(String phoneNumber, String code) {
        boolean register = false;
        if (authCodeStorage.get(phoneNumber) != null && authCodeStorage.get(phoneNumber).equals(code)) {
            User user;
            Optional<User> optionalUser = userService.findByPhone(phoneNumber);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                user = new User();
                user.setPhone(phoneNumber);
                user = userService.save(user);
                register = true;
            }
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);

            saveRefreshToken(user.getId(), refreshToken);

            authCodeStorage.remove(user.getPhone());
            return new JwtResponse(accessToken, refreshToken, register);
        } else {
            throw new CustomException(ExceptionEnum.INCORRECT_AUTH_CODE);
        }
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final Long userId = Long.valueOf(claims.get("userId").toString());
            final List<String> saveRefreshToken = getRefreshToken(userId);
            if (saveRefreshToken != null && saveRefreshToken.contains(refreshToken)) {
                final User user = userService.getById(userId);
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);

                var oldTokenOpt = refreshStorageRepository.findByRefreshToken(refreshToken);
                if (oldTokenOpt.isEmpty()) {
                    log.error("Ошибка обновления существующего refresh токена в бд. Refresh токен: {}", refreshToken);
                    throw new CustomException(ExceptionEnum.SERVER_ERROR);
                }
                var oldToken = oldTokenOpt.get();
                oldToken.setRefreshToken(newRefreshToken);
                refreshStorageRepository.saveAndFlush(oldToken);

                return new JwtResponse(accessToken, newRefreshToken, null);
            }
        }
        throw new CustomException(ExceptionEnum.INVALID_JWT_TOKEN);
    }

    public List<String> getRefreshToken(Long userId) {
        final List<RefreshStorage> refreshStorage = refreshStorageRepository.getAllByUserId(userId);
        return refreshStorage.stream().map(RefreshStorage::getRefreshToken).toList();
    }

    public void saveRefreshToken(Long userId, String refreshToken) {
        var storage = new RefreshStorage();
        storage.setUserId(userId);
        storage.setRefreshToken(refreshToken);
        refreshStorageRepository.saveAndFlush(storage);
    }

    public BufferedImage generateQrCode(String uuid) throws WriterException{
        return QrCodeGenerator.generateQrCode("otpauth://totp/ITMO:BLPS?secret=" + uuid + "&issuer=PHONE");
    }

    public JwtResponse confirmEmail(JwtEmailPassRequest request) {
        var user = userService.getByEmail(request.getEmail());

        var hashedPassword = PasswordHash.hashPassword(request.getPassword());
        if (!user.getPassword().equals(hashedPassword)) {
            throw new CustomException(ExceptionEnum.INCORRECT_PASSWORD);
        }

        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);

        saveRefreshToken(user.getId(), refreshToken);

        return new JwtResponse(accessToken, refreshToken, false);
    }

    public boolean isExist(String phone) {
        return userService.findByPhone(phone).isPresent();
    }
}




