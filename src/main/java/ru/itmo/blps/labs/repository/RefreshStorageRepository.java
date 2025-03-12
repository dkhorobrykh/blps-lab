package ru.itmo.blps.labs.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.blps.labs.domain.RefreshStorage;

public interface RefreshStorageRepository extends JpaRepository<RefreshStorage, Long> {
    Optional<RefreshStorage> findByRefreshToken(String refreshToken);

    List<RefreshStorage> getAllByUserId(Long userId);
}