package ru.itmo.blps.labs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.blps.labs.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);

}