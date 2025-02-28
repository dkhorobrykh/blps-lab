package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdCampaign;
import ru.itmo.blps.labs.repository.AdCampaignRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdCampaignService {

    private final AdCampaignRepository adCampaignRepository;

    public AdCampaign createCampaign(AdCampaign adCampaign) {
        log.info("Creating campaign: {}", adCampaign);
        return adCampaignRepository.save(adCampaign);
    }

    public List<AdCampaign> getCampaignsByUserId(Long userId) {
        log.info("Getting campaigns by user id: {}", userId);
        return adCampaignRepository.findByOwnerId(userId);
    }
}
