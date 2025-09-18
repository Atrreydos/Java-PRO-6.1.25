package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.PaymentRequestDto;
import org.example.dto.payment.PaymentResponseDto;
import org.example.service.ProductClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

  private final ProductClientService productClientService;

  @PostMapping("/process")
  public PaymentResponseDto processPayment(@RequestBody PaymentRequestDto paymentRequest) {
    log.info("Получен запрос выполнения оплаты для пользователя {}", paymentRequest.userId());
    return productClientService.processPayment(paymentRequest);
  }
}
