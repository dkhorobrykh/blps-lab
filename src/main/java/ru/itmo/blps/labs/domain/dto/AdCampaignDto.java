package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import java.io.Serializable;
import java.time.Instant;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.AdCampaign}
 */
@Value
public class AdCampaignDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
    String url;
    @Schema(accessMode = AccessMode.READ_ONLY)
    AdGoalDto adGoal;
    @Schema(accessMode = AccessMode.READ_ONLY)
    AdTypeDto adType;
    @Schema(accessMode = AccessMode.WRITE_ONLY)
    String adGoalName;
    @Schema(accessMode = AccessMode.WRITE_ONLY)
    String adTypeName;
    Integer limitBudget;
    Instant startDate;
    Instant endDate;
    @Schema(accessMode = AccessMode.READ_ONLY)
    Long ownerId;

}