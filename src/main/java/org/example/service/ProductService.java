package org.example.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProductDto;
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
}
