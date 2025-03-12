package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdType;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.AdTypeRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdTypeService {

    private final AdTypeRepository adTypeRepository;

    public AdType getAdType(String name) {
        return adTypeRepository.findByName(name).orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
    }

    public List<AdType> getAllTypes() {
        return adTypeRepository.findAll();
    }
}
