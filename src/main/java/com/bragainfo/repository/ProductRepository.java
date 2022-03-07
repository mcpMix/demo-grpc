package com.bragainfo.repository;

import java.util.Optional;

import com.bragainfo.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

  Iterable<Product> findByNameIgnoreCase(String nome);

}
