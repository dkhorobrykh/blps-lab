package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.DonutGoal;

@Repository
public interface DonutGoalRepository extends JpaRepository<DonutGoal, Long> {

    List<DonutGoal> findAllByCommunityId(Long communityId);
}