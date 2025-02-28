package ru.itmo.blps.labs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmo.blps.labs.utils.AdGroupTargetingConverter;

@Entity
@Table(
    name = "blps_ad_group",
    schema = "s367595"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "JSONB")
    @Convert(converter = AdGroupTargetingConverter.class)
    private List<String> regions;

    private boolean isAllowExpansionOfAudience;

    private Integer minAge;

    private Integer maxAge;

    @Column(columnDefinition = "JSONB")
    @Convert(converter = AdGroupTargetingConverter.class)
    private List<String> interests;

    private boolean isAllowDesktop;

    private boolean isAllowMobile;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ad_campaign_id", nullable = false)
    private AdCampaign adCampaign;

}
