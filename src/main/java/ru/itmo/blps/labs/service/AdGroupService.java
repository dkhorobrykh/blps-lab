package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdGroup;
import ru.itmo.blps.labs.repository.AdGroupRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdGroupService {

    private final AdGroupRepository adGroupRepository;

    public List<AdGroup> getAdGroupsByCampaignId(Long campaignId) {
        log.info("Getting ad groups by campaign id: {}", campaignId);
        return adGroupRepository.findByAdCampaignId(campaignId);
    }

    public AdGroup create(AdGroup adGroup) {
        log.info("Creating ad group: {}", adGroup);
        return adGroupRepository.save(adGroup);
    }
}
