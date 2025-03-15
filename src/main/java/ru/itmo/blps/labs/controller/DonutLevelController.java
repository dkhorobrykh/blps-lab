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
import ru.itmo.blps.labs.domain.dto.DonutLevelDto;
import ru.itmo.blps.labs.domain.mapper.DonutLevelMapper;
import ru.itmo.blps.labs.service.DonutLevelService;

@RestController
@RequestMapping("/donutLevel")
@AllArgsConstructor
public class DonutLevelController {

    private final DonutLevelMapper donutLevelMapper;
    private final DonutLevelService donutLevelService;

    @GetMapping("{communityId}")
    public ResponseEntity<List<DonutLevelDto> > getDonutLevelsByCommunityId(@PathVariable  Long communityId) {
        var result = donutLevelService.getDonutLevelsByCommunityId(communityId);
        return ResponseEntity.ok(donutLevelMapper.toDto(result));
    }

    @PostMapping
    public ResponseEntity<DonutLevelDto> createDonutLevel(@RequestBody @Valid DonutLevelDto donutLevelDto) {
        var donutLevel = donutLevelMapper.toEntity(donutLevelDto);
        var result = donutLevelService.createDonutLevel(donutLevel);
        return ResponseEntity.ok(donutLevelMapper.toDto(result));
    }
}
