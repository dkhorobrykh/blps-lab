package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.AdGoal;
import ru.itmo.blps.labs.domain.dto.AdGoalDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface AdGoalMapper {

    AdGoal toEntity(AdGoalDto adGoalDto);

    AdGoalDto toDto(AdGoal adGoal);

    List<AdGoalDto> toDto(List<AdGoal> adGoals);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdGoal partialUpdate(AdGoalDto adGoalDto, @MappingTarget AdGoal adGoal);
}