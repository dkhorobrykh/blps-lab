package ru.itmo.blps.labs.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.AdTypeDto;
import ru.itmo.blps.labs.domain.mapper.AdTypeMapper;
import ru.itmo.blps.labs.service.AdTypeService;

@RestController
@RequestMapping("/ad-types")
@AllArgsConstructor
public class AdTypeController {

    private final AdTypeService adTypeService;
    private final AdTypeMapper adTypeMapper;

    @GetMapping
    public ResponseEntity<List<AdTypeDto>> getAllTypes() {
        var result = adTypeService.getAllTypes();
        return ResponseEntity.ok(adTypeMapper.toDto(result));
    }
}
