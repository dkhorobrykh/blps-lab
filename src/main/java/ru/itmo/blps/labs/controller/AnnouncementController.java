package ru.itmo.blps.labs.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.AnnouncementStatus;
import ru.itmo.blps.labs.domain.dto.AnnouncementDto;
import ru.itmo.blps.labs.domain.mapper.AnnouncementMapper;
import ru.itmo.blps.labs.service.AnnouncementService;

@RestController
@RequestMapping("/announcement")
@AllArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final AnnouncementMapper announcementMapper;

    @GetMapping("{groupId}")
    public ResponseEntity<List<AnnouncementDto>> getAnnouncementsByGroupId(@PathVariable Long groupId) {
        var result = announcementService.getAnnouncementsByGroupId(groupId);
        return ResponseEntity.ok(announcementMapper.toDto(result));
    }

    @PostMapping("draft")
    public ResponseEntity<AnnouncementDto> createAnnouncementAsDraft(@RequestBody @Valid AnnouncementDto announcementDto) {
        var result = announcementService.createAnnouncement(
            announcementMapper.toEntity(announcementDto),
            AnnouncementStatus.DRAFT
        );
        return ResponseEntity.ok(announcementMapper.toDto(result));
    }

    @PostMapping("created")
    public ResponseEntity<AnnouncementDto> createAnnouncementAsCreated(@RequestBody @Valid AnnouncementDto announcementDto) {
        var result = announcementService.createAnnouncement(
            announcementMapper.toEntity(announcementDto),
            AnnouncementStatus.CREATED
        );
        return ResponseEntity.ok(announcementMapper.toDto(result));
    }

    @PostMapping("{announcementId}/approveByUser")
    public ResponseEntity<AnnouncementDto> approveByUser(@PathVariable Long announcementId) {
        var result = announcementService.approveByUser(announcementId);
        return ResponseEntity.ok(announcementMapper.toDto(result));
    }
}
