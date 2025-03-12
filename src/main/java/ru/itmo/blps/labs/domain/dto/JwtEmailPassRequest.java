package ru.itmo.blps.labs.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class JwtEmailPassRequest {
    @Email
    String email;
    @NotNull
    String password;
}
