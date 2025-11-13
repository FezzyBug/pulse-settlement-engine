package io.hookline.pulse.billing.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record EvaluationRequest(
        @NotBlank String ledgerId,
        @NotNull BigDecimal amount,
        String currency,
        String scenario) {

    public EvaluationRequest {
        if (currency == null || currency.isBlank()) {
            currency = "USD";
        }
        if (scenario == null || scenario.isBlank()) {
            scenario = "standard";
        }
    }
}
