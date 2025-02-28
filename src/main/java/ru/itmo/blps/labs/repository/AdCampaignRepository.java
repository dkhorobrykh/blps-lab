package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.blps.labs.domain.AdCampaign;

public interface AdCampaignRepository extends JpaRepository<AdCampaign, Long> {

    List<AdCampaign> findByOwnerId(Long ownerId);

}