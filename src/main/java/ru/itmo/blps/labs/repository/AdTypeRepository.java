package ru.itmo.blps.labs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.AdType;

@Repository
public interface AdTypeRepository extends JpaRepository<AdType, String> {
    Optional<AdType> findByName(String name);
}