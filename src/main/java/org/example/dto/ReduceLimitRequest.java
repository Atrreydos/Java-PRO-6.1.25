package org.example.dto;

import java.math.BigDecimal;

public record ReduceLimitRequest(
    Long clientId,
    BigDecimal amount) {
}
