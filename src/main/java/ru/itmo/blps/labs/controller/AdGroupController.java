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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.AdGroupDto;
import ru.itmo.blps.labs.domain.mapper.AdGroupMapper;
import ru.itmo.blps.labs.service.AdCampaignService;
import ru.itmo.blps.labs.service.AdGroupService;

@RestController
@RequestMapping("/ad-groups")
@AllArgsConstructor
public class AdGroupController {

    private final AdGroupMapper adGroupMapper;
    private final AdGroupService adGroupService;

    @GetMapping
    public ResponseEntity<List<AdGroupDto>> getAdGroupsByCampaignId(@RequestParam Long campaignId) {
        var result = adGroupService.getAdGroupsByCampaignId(campaignId);
        return ResponseEntity.ok(adGroupMapper.toDto(result));
    }

    @PostMapping
    public ResponseEntity<AdGroupDto> createAdGroup(@RequestBody @Valid AdGroupDto adGroupDto) {
        var result = adGroupService.create(adGroupMapper.toEntity(adGroupDto));
        return ResponseEntity.ok(adGroupMapper.toDto(result));
    }
}
