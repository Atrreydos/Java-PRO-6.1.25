package org.example.dto.product;

import java.math.BigDecimal;
import org.example.enums.ProductType;

public record ProductDto(
    Long id,
    String accountNumber,
    BigDecimal balance,
    ProductType type) {
}
