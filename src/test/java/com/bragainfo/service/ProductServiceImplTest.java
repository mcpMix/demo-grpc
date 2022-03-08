package com.bragainfo.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.bragainfo.converter.ProductRequestDTOToProductConverter;
import com.bragainfo.converter.ProductToProductResponseDTOConverter;
import com.bragainfo.converter.ProductsToProductResponseDTOListConverter;
import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.DateModel;
import com.bragainfo.domain.entity.Product;
import com.bragainfo.exception.ProductAlreadyExistsException;
import com.bragainfo.repository.ProductRepository;
import com.bragainfo.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {


  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductRequestDTOToProductConverter productRequestDTOToProduct;
  @Mock
  private ProductToProductResponseDTOConverter productToProductResponseDTO;
  @Mock
  private ProductsToProductResponseDTOListConverter productsToProductResponseDTOList;

  @InjectMocks
  private ProductServiceImpl service;

  @Test
  public void whenProductCreateSuccess(){

    Product product = loadProduct();

    when(productRequestDTOToProduct.apply(ArgumentMatchers.any())).thenReturn(product);
    when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
    when(productToProductResponseDTO.apply(ArgumentMatchers.any())).thenReturn(loadProductResDTO());

    ProductRequestDTO requestDTO = loadProductReqDTO();
    ProductResponseDTO productResponseDTO = service.create(requestDTO);

    Assertions.assertThat(productResponseDTO)
            .usingRecursiveComparison()
            .comparingOnlyFields("name", "price","quantityInStock")
            .ignoringFieldsOfTypes()
            .ignoringActualNullFields()
            .isEqualTo(product);

    verify(productToProductResponseDTO,times(1)).apply(ArgumentMatchers.any());
    verify(productsToProductResponseDTOList,times(0)).apply(ArgumentMatchers.anySet());

  }

  @Test
  public void whenProductCreateErrorAlreadyExist(){

    Product product = loadProduct();

    when(productRepository.findByNameIgnoreCase(ArgumentMatchers.any())).thenReturn(Optional.of(product));

    ProductRequestDTO requestDTO = loadProductReqDTO();

    assertThatExceptionOfType(ProductAlreadyExistsException.class)
            .isThrownBy(()->service.create(requestDTO));

  }

  private Product loadProduct() {
    Product product = new Product()
        .withName("Nome Test Um")
        .withPrice(20.00)
        .withQuantityInStock(20);
    product.setDateModel( new DateModel(LocalDateTime.now(),LocalDateTime.now()));
    product.setVersion(0);
    return product;
  }

  private ProductRequestDTO loadProductReqDTO() {
    return new ProductRequestDTO()
        .withName("Nome Test Um")
        .withPrice(20.00)
        .withQuantityInStock(20);
  }

  private ProductResponseDTO loadProductResDTO() {
    return new ProductResponseDTO()
        .withName("Nome Test Um")
        .withPrice(20.00)
        .withQuantityInStock(20);
  }


}
