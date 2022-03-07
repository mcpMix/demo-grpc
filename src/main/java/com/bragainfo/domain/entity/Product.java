package com.bragainfo.domain.entity;

import javax.persistence.Entity;

@Entity
public class Product extends DomainModel<Product>{

  private String name;
  private Double price;
  private Integer  quantityInStock;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public Product withName(String name) {
    this.name = name;
    return this;
  }

  public Product withPrice(Double price) {
    this.price = price;
    return this;
  }

  public Product withQuantityInStock(Integer quantityInStock) {
    this.quantityInStock = quantityInStock;
    return this;
  }

  public Product withId(Long id) {
    super.setId(id);
    return this;
  }

}
