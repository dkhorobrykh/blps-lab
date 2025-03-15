package ru.itmo.blps.labs.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.Community;
import ru.itmo.blps.labs.domain.dto.CommunityDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface CommunityMapper {

    Community toEntity(CommunityDto communityDto);

    @Mapping(source = "allowedToMakeDonuts", target = "isAllowedToMakeDonuts")
    CommunityDto toDto(Community community);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Community partialUpdate(CommunityDto communityDto, @MappingTarget Community community);
}