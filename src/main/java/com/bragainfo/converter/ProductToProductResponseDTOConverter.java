package com.bragainfo.converter;

import static java.util.Objects.isNull;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;

import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.Product;

public class ProductToProductResponseDTOConverter implements Function<Product, ProductResponseDTO> {
  public static final String PATTERN_ISO_DATE_TIME = "yyyyMMddHHmmss";

  @Override
  public ProductResponseDTO apply(Product product) {

    if(Objects.isNull(product)){
      return null;
    }

    return  new ProductResponseDTO()
        .withId(product.getId())
        .withName(product.getName())
        .withPrice(product.getPrice())
        .withQuantityInStock(product.getQuantityInStock())
        .withCreateAt(getFormattedDate(product.getDateModel().getCreatedAt(),PATTERN_ISO_DATE_TIME))
        .withUpdateAt(getFormattedDate(product.getDateModel().getUpdatedAt(),PATTERN_ISO_DATE_TIME))
        .withVersion(product.getVersion());
  }

  public static String getFormattedDate(LocalDateTime localDateTime, String pattern) {
    if (isNull(localDateTime) || isBlank(pattern)) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return localDateTime.format(formatter);
  }
}
