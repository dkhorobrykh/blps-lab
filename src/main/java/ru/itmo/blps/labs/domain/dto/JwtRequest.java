package ru.itmo.blps.labs.domain.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    @NotNull
    @NotEmpty
    @Size(max = 11)
    private String phone;
    @NotNull
    @NotEmpty
    @Size(max = 4)
    private String code;

}
