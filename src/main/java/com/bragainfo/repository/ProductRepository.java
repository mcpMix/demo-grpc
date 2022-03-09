package com.bragainfo.repository;

import java.util.Optional;

import com.bragainfo.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByNameIgnoreCase(String nome);

}
