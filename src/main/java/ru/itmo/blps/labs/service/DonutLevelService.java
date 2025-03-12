package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.DonutLevel;
import ru.itmo.blps.labs.repository.DonutLevelRepository;

@Service
@AllArgsConstructor
@Slf4j
public class DonutLevelService {

    private final DonutLevelRepository donutLevelRepository;
    private final CommunityService communityService;

    public List<DonutLevel> getDonutLevelsByCommunityId(Long communityId) {
        return donutLevelRepository.findAllByCommunityId(communityId);
    }

    public DonutLevel createDonutLevel(DonutLevel donutLevel) {
        var community = communityService.getById(donutLevel.getCommunity().getId());
        donutLevel.setCommunity(community);
        return donutLevelRepository.save(donutLevel);
    }
}
