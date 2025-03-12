package ru.itmo.blps.labs.service;

import io.jsonwebtoken.Claims;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.itmo.blps.labs.domain.CustomUserDetails;
import ru.itmo.blps.labs.domain.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static CustomUserDetails generate(Claims claims, UserService userService) {

        final CustomUserDetails userDetails = new CustomUserDetails();

        var user = userService.getById(Long.parseLong(claims.get("userId").toString()));

        userDetails.setUser(user);

        userDetails.setAuthenticated(true);

        return userDetails;
    }

}
