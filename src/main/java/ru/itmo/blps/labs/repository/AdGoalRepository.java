package ru.itmo.blps.labs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.AdGoal;

@Repository
public interface AdGoalRepository extends JpaRepository<AdGoal, String> {

    Optional<AdGoal> findByName(String name);

}