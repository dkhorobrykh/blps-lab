package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.DonutLevel}
 */
@Value
public class DonutLevelDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
    Integer amount;
    String title;
    String description;
    Long communityId;

}