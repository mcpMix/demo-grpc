package com.bragainfo.converter;

import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRequestDTOToProductConverterTest {


  @Test
  public void ProductRequestDTOToProductConverterSuccess(){
    ProductRequestDTO productDTO = new ProductRequestDTO().withName("Nome Test Um").withPrice(20.00).withQuantityInStock(20);
    ProductRequestDTOToProductConverter productRequestDTOToProduct = new ProductRequestDTOToProductConverter();
    Product product = productRequestDTOToProduct.apply(productDTO);

    Assertions.assertThat(productDTO)
        .usingRecursiveComparison()
        .isEqualTo(product);
  }

}
