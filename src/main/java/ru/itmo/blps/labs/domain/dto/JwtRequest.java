package ru.itmo.blps.labs.domain.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    @NotNull
    @NotEmpty
    private String phone;
    @NotNull
    @NotEmpty
    private String code;

}
