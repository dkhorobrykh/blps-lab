package ru.itmo.blps.labs.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ru.itmo.blps.labs.domain.PaymentCard}
 */
@Value
public class PaymentCardDto implements Serializable {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;
    @NotNull
    @NotEmpty
    String token;
    @NotNull
    @NotEmpty
    String type;
}