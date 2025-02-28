package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.AdCampaign;
import ru.itmo.blps.labs.domain.dto.AdCampaignDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING, uses = {
    AdGoalMapper.class,
    AdTypeMapper.class
})
public interface AdCampaignMapper {

    AdCampaign toEntity(AdCampaignDto adCampaignDto);

    AdCampaignDto toDto(AdCampaign adCampaign);

    List<AdCampaignDto> toDto(List<AdCampaign> adCampaigns);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdCampaign partialUpdate(AdCampaignDto adCampaignDto, @MappingTarget AdCampaign adCampaign);
}