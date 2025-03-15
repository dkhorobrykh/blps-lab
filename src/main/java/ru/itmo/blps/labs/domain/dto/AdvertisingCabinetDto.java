package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Schema(defaultValue = "ADVERTISER")
    @NotNull
    @NotEmpty
    AdvertisingCabinetType type;
    @Schema(defaultValue = "RU")
    @NotNull
    @NotEmpty
    String country;
    @Schema(defaultValue = "RUB")
    @NotNull
    @NotEmpty
    String currency;
    @Schema(defaultValue = "primer@example.com")
    @NotNull
    @NotEmpty
    String email;
    @Schema(defaultValue = "INDIVIDUAL")
    @NotNull
    @NotEmpty
    AdvertisingCabinetOwnerType ownerType;
    @NotNull
    @NotEmpty
    String inn;
    @NotNull
    @NotEmpty
    String fullName;
    boolean isAgreeToReceiveAds;
    @Schema(accessMode = AccessMode.READ_ONLY)
    Long ownerId;
}