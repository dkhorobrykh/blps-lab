package ru.itmo.blps.labs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blps_phone_auth_requests", schema = "s367595")
@AllArgsConstructor
@NoArgsConstructor
public class PhoneAuthRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 11)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Size(max = 4)
    @Column(name = "code", length = 4)
    private String code;

    @NotNull
    @Column(name = "datetime", nullable = false)
    private Instant datetime;

    @Column(name = "register")
    private Boolean register;

    @Column(name = "attempts")
    private Integer attempts;
}
