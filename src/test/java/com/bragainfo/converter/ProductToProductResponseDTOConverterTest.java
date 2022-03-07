package com.bragainfo.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.DateModel;
import com.bragainfo.domain.entity.Product;
import org.junit.jupiter.api.Test;

public class ProductToProductResponseDTOConverterTest {

  @Test
  public void ProductRequestDTOToProductConverterSuccess(){
    Product product = new Product()
        .withName("Nome Test Um")
        .withPrice(20.00)
        .withQuantityInStock(20);

    product.setDateModel(new DateModel(LocalDateTime.now(),LocalDateTime.now()));
    product.setVersion(0);

    ProductToProductResponseDTOConverter convert = new ProductToProductResponseDTOConverter();
    ProductResponseDTO productResponse = convert.apply(product);

    assertThat(product);
    assertEquals(product.getName(),productResponse.getName());
    assertEquals(product.getPrice(),productResponse.getPrice());
    assertEquals(product.getQuantityInStock(),productResponse.getQuantityInStock());

  }

}
