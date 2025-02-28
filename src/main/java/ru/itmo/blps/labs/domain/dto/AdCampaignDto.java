package ru.itmo.blps.labs.domain.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.AdCampaign}
 */
@Value
public class AdCampaignDto implements Serializable {

    Long id;
    String url;
    AdGoalDto adGoal;
    AdTypeDto adType;
    Integer limit;
    Instant startDate;
    Instant endDate;
    Long ownerId;

}