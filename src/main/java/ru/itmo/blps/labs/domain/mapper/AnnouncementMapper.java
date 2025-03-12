package ru.itmo.blps.labs.domain.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmo.blps.labs.domain.Announcement;
import ru.itmo.blps.labs.domain.dto.AnnouncementDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = ComponentModel.SPRING,
        uses = {AdGroupMapper.class})
public interface AnnouncementMapper {
    @Mapping(source = "adGroupId", target = "group.id")
    Announcement toEntity(AnnouncementDto announcementDto);

    @Mapping(source = "group", target = "adGroup")
    @Mapping(source = "group.id", target = "adGroupId")
    AnnouncementDto toDto(Announcement announcement);

    List<AnnouncementDto> toDto(List<Announcement> announcements);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Announcement partialUpdate(AnnouncementDto announcementDto, @MappingTarget Announcement announcement);
}