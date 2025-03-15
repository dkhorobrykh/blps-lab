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
import ru.itmo.blps.labs.domain.dto.AdvertisingCabinetDto;
import ru.itmo.blps.labs.domain.mapper.AdvertisingCabinetMapper;
import ru.itmo.blps.labs.service.AdvertisingCabinetService;

@RestController
@RequestMapping("/advertising-cabinet")
@AllArgsConstructor
public class AdvertisingCabinetController {

    private final AdvertisingCabinetMapper advertisingCabinetMapper;
    private final AdvertisingCabinetService advertisingCabinetService;

    @GetMapping("{userId}")
    public ResponseEntity<List<AdvertisingCabinetDto>> getAdvertisingCabinetByUserId(@PathVariable  Long userId) {
        var result = advertisingCabinetService.getByOwnerId(userId);
        return ResponseEntity.ok(advertisingCabinetMapper.toDto(result));
    }

    @PostMapping
    public ResponseEntity<AdvertisingCabinetDto> createAdvertisingCabinet(@RequestBody @Valid AdvertisingCabinetDto advertisingCabinetDto) {
        var result = advertisingCabinetService.create(advertisingCabinetMapper.toEntity(advertisingCabinetDto));
        return ResponseEntity.ok(advertisingCabinetMapper.toDto(result));
    }
}
