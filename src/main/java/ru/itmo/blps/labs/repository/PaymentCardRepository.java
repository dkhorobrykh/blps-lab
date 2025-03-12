package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.PaymentCard;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

    List<PaymentCard> findAllByUserId(Long userId);

}