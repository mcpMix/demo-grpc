package com.bragainfo.service.impl;

import java.util.List;

import com.bragainfo.converter.ProductRequestDTOToProductConverter;
import com.bragainfo.converter.ProductToProductResponseDTOConverter;
import com.bragainfo.converter.ProductsToProductResponseDTOListConverter;
import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.Product;
import com.bragainfo.repository.ProductRepository;
import com.bragainfo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductRequestDTOToProductConverter productRequestDTOToProduct;
  private final ProductToProductResponseDTOConverter productToProductResponseDTO;
  private final ProductsToProductResponseDTOListConverter productsToProductResponseDTOList;

  public ProductServiceImpl(ProductRepository productRepository,
      ProductRequestDTOToProductConverter productRequestDTOToProduct,
      ProductToProductResponseDTOConverter productToProductResponseDTO,
      ProductsToProductResponseDTOListConverter productsToProductResponseDTOList) {
    this.productRepository = productRepository;
    this.productRequestDTOToProduct = productRequestDTOToProduct;
    this.productToProductResponseDTO = productToProductResponseDTO;
    this.productsToProductResponseDTOList = productsToProductResponseDTOList;
  }

  @Override
  public ProductResponseDTO create(ProductRequestDTO product) {
    return productToProductResponseDTO.apply(productRepository.save(productRequestDTOToProduct.apply(product)));
  }

  @Override
  public ProductResponseDTO findById(Long id) {
    return productToProductResponseDTO.apply(productRepository.findById(id).orElse(new Product()));
  }

  @Override
  public void delete(Long id) {
    productRepository.delete(new Product().withId(id));
  }

  @Override
  public List<ProductResponseDTO> findAll() {
    return productsToProductResponseDTOList.apply(productRepository.findAll());
  }

  @Override
  public List<ProductResponseDTO> findByNameIgnoreCase(String name) {
    return productsToProductResponseDTOList.apply(productRepository.findByNameIgnoreCase(name));
  }
}
