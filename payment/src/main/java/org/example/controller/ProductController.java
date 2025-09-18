package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.product.ProductDtoList;
import org.example.service.ProductClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

  private final ProductClientService productClientService;

  @GetMapping("/by-user/{userId}")
  public ProductDtoList getProductsByUser(@PathVariable Long userId) {
    log.info("Получен запрос продуктов по пользователю {}", userId);

    return productClientService.getProductsByUser(userId);
  }
}
