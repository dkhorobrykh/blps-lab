package ru.itmo.blps.labs.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class JwtEmailPassRequest {

    @Email
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String email;
    @NotNull
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String password;
}
