package ru.itmo.blps.labs.service;

import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.PhoneAuthRequest;
import ru.itmo.blps.labs.repository.PhoneAuthRequestRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneAuthRequestService {

    private final PhoneAuthRequestRepository phoneAuthRequestRepository;

    public PhoneAuthRequest save(@NonNull PhoneAuthRequest phoneAuthRequest) {
        return phoneAuthRequestRepository.saveAndFlush(phoneAuthRequest);
    }

    public Optional<PhoneAuthRequest> getLastByPhone(@NonNull String phoneNumber) {
        return phoneAuthRequestRepository.findFirstByPhoneNumberOrderByDatetimeDesc(phoneNumber);
    }
}
