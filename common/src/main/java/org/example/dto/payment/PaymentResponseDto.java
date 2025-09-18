package org.example.dto.payment;

public record PaymentResponseDto(
    boolean paymentSuccess,
    String message) {
}
