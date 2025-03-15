package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.DonutLevel}
 */
@Value
public class DonutLevelDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
    @NotNull
    @Positive
    Integer amount;
    @NotNull
    @NotEmpty
    String title;
    @NotNull
    @NotEmpty
    String description;
    @NotNull
    Long communityId;

}