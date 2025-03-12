package ru.itmo.blps.labs.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    private String phone;
    private String code;
    private String name;

}
