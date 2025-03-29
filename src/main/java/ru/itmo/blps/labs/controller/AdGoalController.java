package ru.itmo.blps.labs.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.AdGoalDto;
import ru.itmo.blps.labs.domain.mapper.AdGoalMapper;
import ru.itmo.blps.labs.service.AdGoalService;

@RestController
@RequestMapping("/ad-goals")
@AllArgsConstructor
public class AdGoalController {
    private final AdGoalService adGoalService;
    private final AdGoalMapper adGoalMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('APPROVE_AD') or hasRole('USER')")
    public ResponseEntity<List<AdGoalDto>> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var result = adGoalService.getAll();
        return ResponseEntity.ok(adGoalMapper.toDto(result));
    }
}
