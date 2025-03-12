package ru.itmo.blps.labs.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.DonutGoalDto;
import ru.itmo.blps.labs.domain.mapper.DonutGoalMapper;
import ru.itmo.blps.labs.service.DonutGoalService;

@RestController
@RequestMapping("/donutGoal")
@AllArgsConstructor
public class DonutGoalController {

    private final DonutGoalService donutGoalService;
    private final DonutGoalMapper donutGoalMapper;

    @GetMapping("{communityId}")
    public ResponseEntity<List<DonutGoalDto>> getDonutGoals(@PathVariable Long communityId) {
        return ResponseEntity.ok(donutGoalMapper.toDto(donutGoalService.getAllByCommunityId(communityId)));
    }

    @PostMapping
    public ResponseEntity<DonutGoalDto> createDonutGoal(@RequestBody DonutGoalDto donutGoalDto) {
        var donutGoal = donutGoalMapper.toEntity(donutGoalDto);
        var result = donutGoalService.createDonutGoal(donutGoal);
        return ResponseEntity.ok(donutGoalMapper.toDto(result));
    }
}
