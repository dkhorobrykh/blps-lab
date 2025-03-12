package ru.itmo.blps.labs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "blps_donut_goal",
    schema = "s367595"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DonutGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer goal;

    private String title;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

}
