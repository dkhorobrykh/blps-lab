package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.blps.labs.domain.DonutLevel;

public interface DonutLevelRepository extends JpaRepository<DonutLevel, Long> {

    List<DonutLevel> findAllByCommunityId(Long communityId);

}