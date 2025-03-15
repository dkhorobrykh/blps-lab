package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.Announcement;
import ru.itmo.blps.labs.domain.AnnouncementStatus;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.AnnouncementRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AdGroupService groupService;

    public List<Announcement> getAnnouncementsByGroupId(Long groupId) {
        return announcementRepository.findAllByGroupId(groupId);
    }

    public Announcement createAnnouncement(Announcement announcement, AnnouncementStatus status) {
        announcement.setStatus(status);
        var group = groupService.getById(announcement.getGroup().getId());
        announcement.setGroup(group);
        return announcementRepository.save(announcement);
    }

    public Announcement approveByUser(Long announcementId) {
        var announcement = announcementRepository.findById(announcementId).orElseThrow(() -> new CustomException(
            ExceptionEnum.NOT_FOUND));
        if (announcement.getStatus() != AnnouncementStatus.CREATED && announcement.getStatus() != AnnouncementStatus.DRAFT) {
            throw new CustomException(ExceptionEnum.ANNOUNCEMENT_IS_NOT_IN_CREATED_OR_DRAFT_STATUS);
        }
        announcement.setStatus(AnnouncementStatus.MODERATION);
        var group = groupService.getById(announcement.getGroup().getId());
        announcement.setGroup(group);
        return announcementRepository.save(announcement);
    }
}
