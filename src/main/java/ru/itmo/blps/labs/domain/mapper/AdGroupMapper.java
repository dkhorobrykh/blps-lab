package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.AdGroup;
import ru.itmo.blps.labs.domain.dto.AdGroupDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface AdGroupMapper {

    @Mapping(source = "campaignId", target = "adCampaign.id")
    AdGroup toEntity(AdGroupDto adGroupDto);

    @Mapping(source = "adCampaign.id", target = "campaignId")
    AdGroupDto toDto(AdGroup adGroup);

    List<AdGroupDto> toDto(List<AdGroup> adGroups);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdGroup partialUpdate(AdGroupDto adGroupDto, @MappingTarget AdGroup adGroup);
}