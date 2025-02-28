package ru.itmo.blps.labs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "blps_advertising_cabinet",
    schema = "s367595"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdvertisingCabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AdvertisingCabinetType type;

    private String country;

    private String currency;

    private String email;

    @Enumerated(EnumType.STRING)
    private AdvertisingCabinetOwnerType ownerType;

    private String inn;

    private String fullName;

    private boolean isAgreeToReceiveAds;

    private Long ownerId;
}
