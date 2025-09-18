package org.example.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.PaymentRequestDto;
import org.example.dto.payment.PaymentResponseDto;
import org.example.dto.product.ProductDto;
import org.example.dto.product.ProductDtoList;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<ProductDto> getAllProducts() {
    log.info("Получен запрос всех продуктов");

    return productService.getAllProducts();
  }

  @GetMapping("/{productId}")
  public ProductDto getProduct(@PathVariable Long productId) {
    log.info("Получен запрос продукта {}", productId);

    return productService.getProduct(productId);
  }

  @GetMapping("/by-user/{userId}")
  public ProductDtoList getProductsByUser(@PathVariable Long userId) {
    log.info("Получен запрос продуктов по пользователю {}", userId);

    return new ProductDtoList(productService.getProductsByUser(userId));
  }

  @PostMapping("/process-payment")
  public PaymentResponseDto processPayment(@RequestBody PaymentRequestDto paymentRequest) {
    log.info("Получен запрос выполнения оплаты для пользователя {}", paymentRequest.userId());

    return productService.processPayment(paymentRequest);
  }
}
