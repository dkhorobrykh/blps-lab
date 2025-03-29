package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 255)
    String title;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String shortDescription;
    @NotNull
    @NotEmpty
    String longDescription;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String textNearButton;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String url;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String textOnButton;
    @NotNull
    @NotEmpty
    String imgUrl;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    String advertiserInfo;
    @Schema(accessMode = AccessMode.READ_ONLY)
    AnnouncementStatus status;
}