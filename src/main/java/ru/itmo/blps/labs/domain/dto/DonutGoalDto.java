package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.DonutGoal}
 */
@Value
public class DonutGoalDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
    @Positive
    @NotNull
    Integer goal;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String title;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String description;
    @Schema(accessMode = AccessMode.READ_ONLY)
    CommunityDto community;
    @Schema(accessMode = AccessMode.WRITE_ONLY)
    @NotNull
    Long communityId;
}