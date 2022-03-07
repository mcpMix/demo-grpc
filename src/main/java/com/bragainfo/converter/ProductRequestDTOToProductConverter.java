package com.bragainfo.converter;

import java.util.Objects;
import java.util.function.Function;

import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestDTOToProductConverter implements Function<ProductRequestDTO, Product> {

  @Override
  public Product apply(ProductRequestDTO productRequestDTO) {
    if(Objects.isNull(productRequestDTO)){
      return null;
    }
    return new Product()
        .withName(productRequestDTO.getName())
        .withPrice(productRequestDTO.getPrice())
        .withQuantityInStock(productRequestDTO.getQuantityInStock());
  }
}
