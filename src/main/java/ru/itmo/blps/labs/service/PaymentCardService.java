package ru.itmo.blps.labs.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.blps.labs.domain.PaymentCard;
import ru.itmo.blps.labs.repository.PaymentCardRepository;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentCardService {

    private final PaymentCardRepository paymentCardRepository;

    public List<PaymentCard> getPaymentCardsByUserId(Long userId) {
        return paymentCardRepository.findAllByUserId(userId);
    }

    public PaymentCard createPaymentCard(PaymentCard paymentCard) {
        paymentCard.setUserId(AuthService.getCurrentUserId());
        return paymentCardRepository.save(paymentCard);
    }
}
