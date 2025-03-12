package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.Community;
import ru.itmo.blps.labs.domain.DonutGoal;
import ru.itmo.blps.labs.repository.CommunityRepository;
import ru.itmo.blps.labs.repository.DonutGoalRepository;

@Service
@AllArgsConstructor
@Slf4j
public class DonutGoalService {

    private final DonutGoalRepository donutGoalRepository;
    private final CommunityService communityService;

    public List<DonutGoal> getAllByCommunityId(Long communityId) {
        return donutGoalRepository.findAllByCommunityId(communityId);
    }

    public DonutGoal createDonutGoal(DonutGoal donutGoal) {
        var community = communityService.getById(donutGoal.getCommunity().getId());
        donutGoal.setCommunity(community);
        return donutGoalRepository.save(donutGoal);
    }
}
