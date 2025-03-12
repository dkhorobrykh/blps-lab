package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.DonutLevel;
import ru.itmo.blps.labs.domain.dto.DonutLevelDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface DonutLevelMapper {

    @Mapping(source = "communityId", target = "community.id")
    DonutLevel toEntity(DonutLevelDto donutLevelDto);

    @Mapping(source = "community.id", target = "communityId")
    DonutLevelDto toDto(DonutLevel donutLevel);

    List<DonutLevelDto> toDto(List<DonutLevel> donutLevels);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DonutLevel partialUpdate(DonutLevelDto donutLevelDto, @MappingTarget DonutLevel donutLevel);
}