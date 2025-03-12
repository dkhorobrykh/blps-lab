package ru.itmo.blps.labs.controller;

import static java.awt.SystemColor.text;

import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.AuthRequest;
import ru.itmo.blps.labs.domain.dto.AuthResponse;
import ru.itmo.blps.labs.domain.dto.JwtEmailPassRequest;
import ru.itmo.blps.labs.domain.dto.JwtRequest;
import ru.itmo.blps.labs.domain.dto.JwtResponse;
import ru.itmo.blps.labs.domain.dto.RefreshJwtRequest;
import ru.itmo.blps.labs.service.AuthService;
import ru.itmo.blps.labs.utils.PasswordHash;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("request")
    @Operation(summary = "Запросить код авторизации")
    public ResponseEntity<AuthResponse> request(@Valid @RequestBody AuthRequest authRequest) {
        final AuthResponse authResponse = authService.request(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("confirm")
    @Operation(summary = "Подтвердить код авторизации, получить токены")
    public ResponseEntity<JwtResponse> confirm(@Valid @RequestBody JwtRequest jwtRequest) {
        final JwtResponse token = authService.confirm(jwtRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    @Operation(summary = "Обновить токены")
    public ResponseEntity<JwtResponse> getNewTokens(@Valid @RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @GetMapping("generate-qr")
    @Operation(summary = "Сгенерировать QR-код для авторизации")
    public ResponseEntity<byte[]> generateQr(@RequestParam String uuid) throws WriterException, IOException {
        BufferedImage qrImage = authService.generateQrCode(uuid);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(baos.size());

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("confirm-qr")
    @Operation(summary = "Подтвердить QR-код, получить токены")
    public ResponseEntity<JwtResponse> confirmQr(@RequestParam Long userId) {
        final JwtResponse token = authService.confirmQr(userId);
        return ResponseEntity.ok(token);
    }

    @PostMapping("confirm-email")
    @Operation(summary = "Авторизоваться по email")
    public ResponseEntity<JwtResponse> confirmEmail(@Valid @RequestBody JwtEmailPassRequest request) {
        final JwtResponse token = authService.confirmEmail(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping("is-exist")
    @Operation(summary = "Проверить существование пользователя")
    public ResponseEntity<Boolean> isExist(@RequestParam String phone) {
        final boolean isExist = authService.isExist(phone);
        return ResponseEntity.ok(isExist);
    }

}
