package ru.itmo.blps.labs.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    @NotNull
    @NotEmpty
    private String phone;
    @Size(max = 255)
    private String name;
}
