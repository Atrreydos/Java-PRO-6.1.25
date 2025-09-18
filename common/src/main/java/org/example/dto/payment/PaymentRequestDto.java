package org.example.dto.payment;

import java.math.BigDecimal;

public record PaymentRequestDto(
    Long userId,
    Long productId,
    BigDecimal amount) {
}
