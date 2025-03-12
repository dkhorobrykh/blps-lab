package ru.itmo.blps.labs.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itmo.blps.labs.domain.User;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;

@Slf4j
@Component
public class JwtProvider {

    private final PrivateKey accessPrivate;
    private final RSAPublicKey accessPublic;

    private final PrivateKey refreshPrivate;
    private final RSAPublicKey refreshPublic;

    @Value("${jwt.access.life-time}")
    private Integer accessTokenLifetime;
    @Value("${jwt.refresh.life-time}")
    private Integer refreshTokenLifetime;

    public JwtProvider(
        @Value("${jwt.access.private}") String accessPr,
        @Value("${jwt.access.public}") String accessPub,
        @Value("${jwt.refresh.private}") String refreshPr,
        @Value("${jwt.refresh.public}") String refreshPub
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecAPr = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(accessPr.getBytes()));
        PrivateKey accessPrivateKey = kf.generatePrivate(keySpecAPr);
        X509EncodedKeySpec keySpecAPub = new X509EncodedKeySpec(Base64.getDecoder().decode(accessPub.getBytes()));
        RSAPublicKey accessPublicKey = (RSAPublicKey) kf.generatePublic(keySpecAPub);

        PKCS8EncodedKeySpec keySpecRPr = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(refreshPr.getBytes()));
        PrivateKey refreshPrivateKey = kf.generatePrivate(keySpecRPr);
        X509EncodedKeySpec keySpecRPub = new X509EncodedKeySpec(Base64.getDecoder().decode(refreshPub.getBytes()));
        RSAPublicKey refreshPublicKey = (RSAPublicKey) kf.generatePublic(keySpecRPub);

        this.accessPrivate = accessPrivateKey;
        this.accessPublic = accessPublicKey;
        this.refreshPrivate = refreshPrivateKey;
        this.refreshPublic = refreshPublicKey;
    }


    public String generateAccessToken(@NonNull User user) {
        final Instant accessExpirationInstant = Instant.now().plus(accessTokenLifetime, ChronoUnit.MINUTES);
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts
            .builder()
            .setExpiration(accessExpiration)
            .signWith(accessPrivate, SignatureAlgorithm.RS256)
            .claim("userId", user.getId())
            .compact();
    }

    public String generateRefreshToken(@NonNull User user) {
        final Instant refreshExpirationInstant = Instant.now().plus(refreshTokenLifetime, ChronoUnit.MINUTES);
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts
            .builder()
            .setExpiration(refreshExpiration)
            .signWith(refreshPrivate, SignatureAlgorithm.RS256)
            .claim("userId", user.getId())
            .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, accessPublic);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, refreshPublic);
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.info("Token {} expired", token);
            throw new CustomException(ExceptionEnum.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException unsEx) {
            log.info("Unsupported jwt token {}", token);
            throw new CustomException(ExceptionEnum.UNSUPPORTED_JWT);
        } catch (MalformedJwtException mjEx) {
            log.info("Malformed jwt token {}", token);
            throw new CustomException(ExceptionEnum.MALFORMED_JWT);
        } catch (SignatureException sEx) {
            log.info("Invalid token {} signature", token);
            throw new CustomException(ExceptionEnum.INVALID_SIGNATURE);
        } catch (Exception e) {
            log.info("Invalid token {}", token);
            throw new CustomException(ExceptionEnum.INVALID_JWT_TOKEN);
        }
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, accessPublic);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, refreshPublic);
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

}
