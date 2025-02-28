package ru.itmo.blps.labs.domain.dto;

import java.io.Serializable;
import lombok.Value;
import ru.itmo.blps.labs.domain.AdvertisingCabinet;
import ru.itmo.blps.labs.domain.AdvertisingCabinetOwnerType;
import ru.itmo.blps.labs.domain.AdvertisingCabinetType;

/**
 * DTO for {@link AdvertisingCabinet}
 */
@Value
public class AdvertisingCabinetAddDto implements Serializable {

    AdvertisingCabinetType type;
    String country;
    String currency;
    String email;
    AdvertisingCabinetOwnerType ownerType;
    String inn;
    String fullName;
    boolean isAgreeToReceiveAds;
}