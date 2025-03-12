package ru.itmo.blps.labs.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdGoal;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.AdGoalRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdGoalService {

    private final AdGoalRepository adGoalRepository;

    public AdGoal getByName(String name) {
        return adGoalRepository.findByName(name)
                .orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
    }
}
