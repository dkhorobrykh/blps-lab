package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.DonutGoal;
import ru.itmo.blps.labs.domain.dto.DonutGoalDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface DonutGoalMapper {

    @Mapping(source = "communityId", target = "community.id")
    DonutGoal toEntity(DonutGoalDto donutGoalDto);

    @Mapping(source = "community.id", target = "communityId")
    DonutGoalDto toDto(DonutGoal donutGoal);

    List<DonutGoalDto> toDto(List<DonutGoal> donutGoals);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DonutGoal partialUpdate(DonutGoalDto donutGoalDto, @MappingTarget DonutGoal donutGoal);
}