package ru.itmo.blps.labs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "blps_ad_type",
    schema = "s367595"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdType {
    @Id
    private String name;

    private String description;

    private String imgUrl;
}
