package ru.itmo.blps.labs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "blps_ad_campaign",
    schema = "s367595"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ad_goal_name", nullable = false)
    private AdGoal adGoal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ad_type_name", nullable = false)
    private AdType adType;

    @Column(name = "limit_budget")
    private Integer limitBudget;

    private Instant startDate;

    private Instant endDate;

    private Long ownerId;

}
