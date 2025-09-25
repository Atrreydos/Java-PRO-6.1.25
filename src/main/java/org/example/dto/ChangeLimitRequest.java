package org.example.dto;

import java.math.BigDecimal;

public record ChangeLimitRequest(
    Long clientId,
    BigDecimal amount) {
}
