package ru.itmo.blps.labs.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blps.labs.domain.dto.PaymentCardDto;
import ru.itmo.blps.labs.domain.mapper.PaymentCardMapper;
import ru.itmo.blps.labs.service.PaymentCardService;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentCardService paymentCardService;
    private final PaymentCardMapper paymentCardMapper;

    @GetMapping
    public ResponseEntity<List<PaymentCardDto>> getPaymentCardsByUserId(@RequestParam Long userId) {
        var result = paymentCardService.getPaymentCardsByUserId(userId);
        return ResponseEntity.ok(paymentCardMapper.toDto(result));
    }

    @PostMapping
    public ResponseEntity<PaymentCardDto> createPaymentCard(@RequestBody @Valid PaymentCardDto paymentCardDto) {
        var paymentCard = paymentCardMapper.toEntity(paymentCardDto);
        var result = paymentCardService.createPaymentCard(paymentCard);
        return ResponseEntity.ok(paymentCardMapper.toDto(result));
    }
}
