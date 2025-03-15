package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    List<String> regions;
    boolean isAllowExpansionOfAudience;
    @Positive
    Integer minAge;
    @Positive
    Integer maxAge;
    @NotNull
    List<String> interests;
    boolean isAllowDesktop;
    boolean isAllowMobile;
    @Schema(accessMode = AccessMode.WRITE_ONLY)
    @NotNull
    Long campaignId;
}