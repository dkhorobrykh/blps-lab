package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdCampaign;
import ru.itmo.blps.labs.domain.AdGroup;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.AdGroupRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdGroupService {

    private final AdGroupRepository adGroupRepository;
    private final AdCampaignService adCampaignService;

    public List<AdGroup> getAdGroupsByCampaignId(Long campaignId) {
        log.info("Getting ad groups by campaign id: {}", campaignId);
        return adGroupRepository.findByAdCampaignId(campaignId);
    }

    public AdGroup create(AdGroup adGroup) {
        var campaign = adCampaignService.getById(adGroup.getAdCampaign().getId());
        adGroup.setAdCampaign(campaign);
        return adGroupRepository.save(adGroup);
    }

    public AdGroup getById(Long id) {
        return adGroupRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
    }
}
