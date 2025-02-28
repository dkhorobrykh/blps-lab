package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.AdvertisingCabinet;
import ru.itmo.blps.labs.domain.dto.AdvertisingCabinetDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface AdvertisingCabinetMapper {

    AdvertisingCabinet toEntity(AdvertisingCabinetDto advertisingCabinetDto);

    AdvertisingCabinetDto toDto(AdvertisingCabinet advertisingCabinet);

    List<AdvertisingCabinetDto> toDto(List<AdvertisingCabinet> advertisingCabinets);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdvertisingCabinet partialUpdate(
        AdvertisingCabinetDto advertisingCabinetDto,
        @MappingTarget AdvertisingCabinet advertisingCabinet
    );
}