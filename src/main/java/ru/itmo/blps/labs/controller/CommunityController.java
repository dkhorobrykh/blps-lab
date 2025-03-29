package ru.itmo.blps.labs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.CommunityDto;
import ru.itmo.blps.labs.domain.mapper.CommunityMapper;
import ru.itmo.blps.labs.service.CommunityService;

@RestController
@RequestMapping("/community")
@AllArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final CommunityMapper communityMapper;

    @GetMapping("{communityId}")
    public ResponseEntity<CommunityDto> getCommunityById(@PathVariable Long communityId) {
        var result = communityService.getById(communityId);
        return ResponseEntity.ok(communityMapper.toDto(result));
    }

    @PostMapping("{communityId}/donuts-status")
    public ResponseEntity<CommunityDto> activateDonuts(@PathVariable Long communityId) {
        var result = communityService.activateDonuts(communityId);
        return ResponseEntity.ok(communityMapper.toDto(result));
    }
}
