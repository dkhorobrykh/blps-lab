package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.AdCampaign;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.AdCampaignRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AdCampaignService {

    private final AdCampaignRepository adCampaignRepository;
    private final AdTypeService adTypeService;
    private final AdGoalService adGoalService;

    public AdCampaign createCampaign(AdCampaign adCampaign) {
        adCampaign.setOwnerId(AuthService.getCurrentUserId());
        var adType = adTypeService.getAdType(adCampaign.getAdType().getName());
        adCampaign.setAdType(adType);
        var adGoal = adGoalService.getByName(adCampaign.getAdGoal().getName());
        adCampaign.setAdGoal(adGoal);
        if (adCampaign.getEndDate().isBefore(adCampaign.getStartDate())) {
            throw new CustomException(ExceptionEnum.INVALID_DATE);
        }
        return adCampaignRepository.save(adCampaign);
    }

    public List<AdCampaign> getCampaignsByUserId(Long userId) {
        return adCampaignRepository.findByOwnerId(userId);
    }

    public AdCampaign getById(Long id) {
        return adCampaignRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionEnum.NOT_FOUND));
    }
}
