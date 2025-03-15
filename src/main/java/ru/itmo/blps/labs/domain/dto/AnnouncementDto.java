package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Value;
import ru.itmo.blps.labs.domain.AnnouncementStatus;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.Announcement}
 */
@Value
public class AnnouncementDto implements Serializable {

    @Schema(accessMode = AccessMode.READ_ONLY)
    Long id;
    @Schema(accessMode = AccessMode.READ_ONLY)
    AdGroupDto adGroup;
    @Schema(accessMode = AccessMode.WRITE_ONLY)
    @NotNull
    Long adGroupId;
    @NotNull
    @NotEmpty
    String title;
    @NotNull
    @NotEmpty
    String shortDescription;
    @NotNull
    @NotEmpty
    String longDescription;
    @NotNull
    @NotEmpty
    String textNearButton;
    @NotNull
    @NotEmpty
    String url;
    @NotNull
    @NotEmpty
    String textOnButton;
    @NotNull
    @NotEmpty
    String imgUrl;
    @NotNull
    @NotEmpty
    String advertiserInfo;
    @Schema(accessMode = AccessMode.READ_ONLY)
    AnnouncementStatus status;
}