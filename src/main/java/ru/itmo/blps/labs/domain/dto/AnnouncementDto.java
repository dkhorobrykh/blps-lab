package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
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
    Long adGroupId;
    String title;
    String shortDescription;
    String longDescription;
    String textNearButton;
    String url;
    String textOnButton;
    String imgUrl;
    String advertiserInfo;
    @Schema(accessMode = AccessMode.READ_ONLY)
    AnnouncementStatus status;
}