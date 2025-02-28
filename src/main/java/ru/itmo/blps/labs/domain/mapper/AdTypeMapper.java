package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.AdType;
import ru.itmo.blps.labs.domain.dto.AdTypeDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface AdTypeMapper {

    AdType toEntity(AdTypeDto adTypeDto);

    AdTypeDto toDto(AdType adType);

    List<AdTypeDto> toDto(List<AdType> adTypes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdType partialUpdate(AdTypeDto adTypeDto, @MappingTarget AdType adType);
}