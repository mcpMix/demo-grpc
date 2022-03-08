package com.bragainfo.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.bragainfo.converter.ProductRequestDTOToProductConverter;
import com.bragainfo.converter.ProductToProductResponseDTOConverter;
import com.bragainfo.converter.ProductsToProductResponseDTOListConverter;
import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.Product;
import com.bragainfo.exception.ProductAlreadyExistsException;
import com.bragainfo.repository.ProductRepository;
import com.bragainfo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

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
  public ProductResponseDTO create(ProductRequestDTO productReqDTO) {
    LOGGER.info("stage=init method=ProductServiceImpl.create message= Begin create product request={}", productReqDTO);
    if(Objects.isNull(productReqDTO)){
      return null;
    }
    ckeckDuplicity(productReqDTO.getName());
    ProductResponseDTO dtoResult = productToProductResponseDTO.apply(productRepository.save(productRequestDTOToProduct.apply(productReqDTO)));

    LOGGER.info("stage=end method=ProductServiceImpl.create message= Finish create product response={}",dtoResult);
    return dtoResult;
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


  private void ckeckDuplicity(String name){
   this.productRepository.findByNameIgnoreCase(name)
       .ifPresent(exp->{
            throw new ProductAlreadyExistsException(name);
        });
  }
}
