package org.example.dto;

import java.math.BigDecimal;

public record LimitDto(
    Long id,
    Long clientId,
    BigDecimal limit) {
}
