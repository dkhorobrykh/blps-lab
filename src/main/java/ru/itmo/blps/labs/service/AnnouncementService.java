package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.Announcement;
import ru.itmo.blps.labs.domain.AnnouncementStatus;
import ru.itmo.blps.labs.repository.AnnouncementRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<Announcement> getAnnouncementsByGroupId(Long groupId) {
        return announcementRepository.findAllByGroupId(groupId);
    }

    public Announcement createAnnouncement(Announcement announcement, AnnouncementStatus status) {
        announcement.setStatus(status);
        return announcementRepository.save(announcement);
    }

    public Announcement approveByUser(Long announcementId) {
        var announcement = announcementRepository.findById(announcementId).orElseThrow();
        announcement.setStatus(AnnouncementStatus.MODERATION);
        return announcementRepository.save(announcement);
    }
}
