package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.blps.labs.domain.AdGroup;

public interface AdGroupRepository extends JpaRepository<AdGroup, Long> {

    List<AdGroup> findByAdCampaignId(Long campaignId);

}