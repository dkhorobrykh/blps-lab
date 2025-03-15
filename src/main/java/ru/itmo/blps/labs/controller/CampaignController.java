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
import ru.itmo.blps.labs.domain.dto.AdCampaignDto;
import ru.itmo.blps.labs.domain.mapper.AdCampaignMapper;
import ru.itmo.blps.labs.service.AdCampaignService;

@RestController
@RequestMapping("/campaign")
@AllArgsConstructor
public class CampaignController {

    private final AdCampaignMapper adCampaignMapper;
    private final AdCampaignService adCampaignService;

    @PostMapping
    public ResponseEntity<AdCampaignDto> createCampaign(@RequestBody @Valid AdCampaignDto adCampaignDto) {
        var result = adCampaignService.createCampaign(adCampaignMapper.toEntity(adCampaignDto));
        return ResponseEntity.ok(adCampaignMapper.toDto(result));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<AdCampaignDto>> getCampaignsByUserId(@PathVariable Long userId) {
        var result = adCampaignService.getCampaignsByUserId(userId);
        return ResponseEntity.ok(adCampaignMapper.toDto(result));
    }
}
