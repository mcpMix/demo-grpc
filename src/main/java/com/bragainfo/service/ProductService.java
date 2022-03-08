package com.bragainfo.service;

import java.util.List;

import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.dto.ProductResponseDTO;

public interface ProductService {

  ProductResponseDTO create(ProductRequestDTO product);
  ProductResponseDTO findById(Long id);
  void delete(Long id);
  List<ProductResponseDTO> findAll();
}
