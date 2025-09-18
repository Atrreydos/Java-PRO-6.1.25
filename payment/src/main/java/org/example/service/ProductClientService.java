package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.PaymentRequestDto;
import org.example.dto.payment.PaymentResponseDto;
import org.example.dto.product.ProductDtoList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductClientService {

  private final RestTemplate restTemplate;

  public ProductDtoList getProductsByUser(Long userId) {
    log.info("Выполнение запроса всех продуктов из сервиса продуктов для пользователя {}", userId);
    return restTemplate.getForObject("/products/by-user/{userId}", ProductDtoList.class, userId);
  }

  public PaymentResponseDto processPayment(PaymentRequestDto paymentRequest) {
    log.info("Выполнение запроса обработки оплаты из сервиса продуктов для пользователя {}", paymentRequest.userId());
    return restTemplate.postForObject("/products/process-payment", paymentRequest, PaymentResponseDto.class);
  }
}
