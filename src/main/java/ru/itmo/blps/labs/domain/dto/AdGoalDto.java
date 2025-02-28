package ru.itmo.blps.labs.domain.dto;

import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.AdGoal}
 */
@Value
public class AdGoalDto implements Serializable {

    String name;
    String description;
}