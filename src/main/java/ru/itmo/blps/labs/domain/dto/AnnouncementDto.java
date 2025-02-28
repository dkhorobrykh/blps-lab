package ru.itmo.blps.labs.domain.dto;

import java.io.Serializable;
import lombok.Value;
import ru.itmo.blps.labs.domain.AnnouncementStatus;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.Announcement}
 */
@Value
public class AnnouncementDto implements Serializable {

    Long id;
    AdGroupDto adGroup;
    String title;
    String shortDescription;
    String longDescription;
    String textNearButton;
    String url;
    String textOnButton;
    String imgUrl;
    String advertiserInfo;
    AnnouncementStatus status;
}