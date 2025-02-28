package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.AdGroup}
 */
@Value
public class AdGroupDto implements Serializable {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;
    List<String> regions;
    boolean isAllowExpansionOfAudience;
    Integer minAge;
    Integer maxAge;
    List<String> interests;
    boolean isAllowDesktop;
    boolean isAllowMobile;
}