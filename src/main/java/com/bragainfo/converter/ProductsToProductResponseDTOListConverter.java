package com.bragainfo.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductsToProductResponseDTOListConverter implements Function<Iterable<Product>, List<ProductResponseDTO>> {

  private final ProductToProductResponseDTOConverter productToProductResponseDTO;

  public ProductsToProductResponseDTOListConverter(ProductToProductResponseDTOConverter productToProductResponseDTO) {
    this.productToProductResponseDTO = productToProductResponseDTO;
  }

  @Override
  public List<ProductResponseDTO> apply(Iterable<Product> products) {

    if(Objects.isNull(products)){
      return null;
    }

    List<ProductResponseDTO> result = new ArrayList<>();
    Iterator<Product> ite = products.iterator();
    while(ite.hasNext()){
      result.add(productToProductResponseDTO.apply(ite.next()));
    }
    return result;
  }

}
