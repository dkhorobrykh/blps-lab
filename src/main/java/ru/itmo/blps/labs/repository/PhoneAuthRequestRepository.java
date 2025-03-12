package ru.itmo.blps.labs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.blps.labs.domain.PhoneAuthRequest;

public interface PhoneAuthRequestRepository extends JpaRepository<PhoneAuthRequest, Long> {

    Optional<PhoneAuthRequest> findFirstByPhoneNumberOrderByDatetimeDesc(String phoneNumber);

}