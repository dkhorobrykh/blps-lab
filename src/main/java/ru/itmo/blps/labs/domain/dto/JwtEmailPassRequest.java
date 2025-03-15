package ru.itmo.blps.labs.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class JwtEmailPassRequest {

    @Email
    @NotNull
    @NotEmpty
    String email;
    @NotNull
    @NotNull
    @NotEmpty
    String password;
}
