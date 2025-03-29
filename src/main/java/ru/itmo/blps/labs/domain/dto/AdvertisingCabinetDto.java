package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    AdvertisingCabinetType type;
    @Schema(defaultValue = "RU")
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String country;
    @Schema(defaultValue = "RUB")
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String currency;
    @Schema(defaultValue = "primer@example.com")
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String email;
    @Schema(defaultValue = "INDIVIDUAL")
    @NotNull
    AdvertisingCabinetOwnerType ownerType;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String inn;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String fullName;
    boolean isAgreeToReceiveAds;
    @Schema(accessMode = AccessMode.READ_ONLY)
    Long ownerId;
}