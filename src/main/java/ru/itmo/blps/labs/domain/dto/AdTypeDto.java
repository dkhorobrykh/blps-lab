package ru.itmo.blps.labs.domain.dto;

import java.io.Serializable;
import lombok.Value;
import ru.itmo.blps.labs.domain.AdType;

/**
 * DTO for {@link AdType}
 */
@Value
public class AdTypeDto implements Serializable {

    String name;
    String description;
    String imgUrl;
}