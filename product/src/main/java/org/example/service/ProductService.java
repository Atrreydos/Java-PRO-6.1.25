package org.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.PaymentRequestDto;
import org.example.dto.payment.PaymentResponseDto;
import org.example.dto.product.ProductDto;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<ProductDto> getAllProducts() {
    log.info("Получение всех продуктов");
    var products = productRepository.findAll();

    return products.stream()
        .map(p -> new ProductDto(p.getId(), p.getAccountNumber(), p.getBalance(), p.getType()))
        .toList();
  }

  public ProductDto getProduct(Long productId) {
    log.info("Получение продукта {}", productId);
    var product = productRepository.getReferenceById(productId);

    return new ProductDto(product.getId(), product.getAccountNumber(), product.getBalance(), product.getType());
  }

  public List<ProductDto> getProductsByUser(Long userId) {
    log.info("Получение всех продуктов для пользователя {}", userId);
    var products = productRepository.findAllByUserId(userId);

    return products.stream()
        .map(p -> new ProductDto(p.getId(), p.getAccountNumber(), p.getBalance(), p.getType()))
        .toList();
  }

  public PaymentResponseDto processPayment(PaymentRequestDto paymentRequest) {
    var productOpt = productRepository.findByUserIdAndId(paymentRequest.userId(), paymentRequest.productId());
    if (productOpt.isEmpty()) {
      return new PaymentResponseDto(false, "У пользователя отсутствует указанный продукт.");
    }

    var product = productOpt.get();
    if (product.getBalance().compareTo(paymentRequest.amount()) < 0) {
      return new PaymentResponseDto(false, "Недостаточный баланс продукта.");
    }

    product.setBalance(product.getBalance().subtract(paymentRequest.amount()));
    productRepository.save(product);

    return new PaymentResponseDto(true, "Платеж выполнен.");
  }
}
