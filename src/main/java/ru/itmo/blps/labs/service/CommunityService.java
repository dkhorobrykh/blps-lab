package ru.itmo.blps.labs.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.Community;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.CommunityRepository;

@Service
@AllArgsConstructor
@Slf4j
public class CommunityService {

    private final CommunityRepository communityRepository;

    public Community getById(Long communityId) {
        return communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
    }

    public Community activateDonuts(Long communityId) {
        var community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
        community.setAllowedToMakeDonuts(true);
        return communityRepository.save(community);
    }
}
