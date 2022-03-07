package com.bragainfo.service;

import java.util.List;
import java.util.Optional;

import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.domain.entity.Product;

public interface ProductService {

  ProductResponseDTO create(ProductRequestDTO product);
  ProductResponseDTO findById(Long id);
  void delete(Long id);
  List<ProductResponseDTO> findAll();
  List<ProductResponseDTO> findByNameIgnoreCase(String nome);


}
