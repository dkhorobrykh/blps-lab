package ru.itmo.blps.labs.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.User;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.repository.UserRepository;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ExceptionEnum.USER_NOT_FOUND));
    }

    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ExceptionEnum.USER_NOT_FOUND));
    }
}
