package com.bragainfo.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.DateModel;
import com.bragainfo.domain.entity.Product;
import org.junit.jupiter.api.Test;

public class ProductsToProductResponseDTOListConverterTest {

  @Test
  public void ProductsToProductResponseDTOListConverterSuccess(){
    Product product = getProduct();

    Set<Product> products = new HashSet<>();
    products.add(product);

    ProductsToProductResponseDTOListConverter convert = new ProductsToProductResponseDTOListConverter(new ProductToProductResponseDTOConverter());
    List<ProductResponseDTO> productResponses = convert.apply(products);

    assertThat(product);
    assertEquals(products.size(),productResponses.size());


  }

  private Product getProduct() {
    Product product = new Product()
        .withName("Nome Test Um")
        .withPrice(20.00)
        .withQuantityInStock(20);
    product.setDateModel(new DateModel(LocalDateTime.now(),LocalDateTime.now()));
    product.setVersion(0);
    return product;
  }

}
