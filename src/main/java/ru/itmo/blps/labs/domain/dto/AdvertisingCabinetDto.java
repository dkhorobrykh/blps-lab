package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serializable;
import lombok.Value;
import ru.itmo.blps.labs.domain.AdvertisingCabinet;
import ru.itmo.blps.labs.domain.AdvertisingCabinetOwnerType;
import ru.itmo.blps.labs.domain.AdvertisingCabinetType;

/**
 * DTO for {@link AdvertisingCabinet}
 */
@Value
public class AdvertisingCabinetDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
//    @Schema(defaultValue = "ADVERTISER")
    AdvertisingCabinetType type;
//    @Schema(defaultValue = "RU")
    String country;
//    @Schema(defaultValue = "RUB")
    String currency;
//    @Schema(defaultValue = "primer@example.com")
    String email;
//    @Schema(defaultValue = "INDIVIDUAL")
    AdvertisingCabinetOwnerType ownerType;
    String inn;
    String fullName;
    boolean isAgreeToReceiveAds;
    @Schema(accessMode = AccessMode.READ_ONLY)
    Long ownerId;
}