package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.DonutGoal}
 */
@Value
public class DonutGoalDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
    Integer goal;
    String title;
    String description;
    @Schema(accessMode = AccessMode.READ_ONLY)
    CommunityDto community;
    @Schema(accessMode = AccessMode.WRITE_ONLY)
    Long communityId;
}