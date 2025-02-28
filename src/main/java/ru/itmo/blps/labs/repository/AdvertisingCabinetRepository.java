package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.AdvertisingCabinet;

@Repository
public interface AdvertisingCabinetRepository extends JpaRepository<AdvertisingCabinet, Long> {
    List<AdvertisingCabinet> findByOwnerId(Long userId);
}