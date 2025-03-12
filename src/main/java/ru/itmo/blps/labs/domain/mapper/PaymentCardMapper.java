package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.PaymentCard;
import ru.itmo.blps.labs.domain.dto.PaymentCardDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface PaymentCardMapper {

    PaymentCard toEntity(PaymentCardDto paymentCardDto);

    PaymentCardDto toDto(PaymentCard paymentCard);

    List<PaymentCardDto> toDto(List<PaymentCard> paymentCards);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentCard partialUpdate(PaymentCardDto paymentCardDto, @MappingTarget PaymentCard paymentCard);
}