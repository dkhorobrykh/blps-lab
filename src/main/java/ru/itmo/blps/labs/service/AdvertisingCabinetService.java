package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdvertisingCabinet;
import ru.itmo.blps.labs.repository.AdvertisingCabinetRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdvertisingCabinetService {

    private final AdvertisingCabinetRepository advertisingCabinetRepository;

    public List<AdvertisingCabinet> getByOwnerId(Long userId) {
        return advertisingCabinetRepository.findByOwnerId(userId);
    }

    public AdvertisingCabinet create(AdvertisingCabinet advertisingCabinet) {
        advertisingCabinet.setOwnerId(AuthService.getCurrentUserId());
        return advertisingCabinetRepository.save(advertisingCabinet);
    }
}
