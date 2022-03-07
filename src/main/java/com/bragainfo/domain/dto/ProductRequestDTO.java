package com.bragainfo.domain.dto;

public class ProductRequestDTO extends Request{

  private  String name;
  private  Double price;
  private  Integer  quantityInStock;

  public String getName() {
    return name;
  }

  public ProductRequestDTO withName(String name) {
    this.name = name;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public ProductRequestDTO withPrice(Double price) {
    this.price = price;
    return this;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public ProductRequestDTO withQuantityInStock(Integer quantityInStock) {
    this.quantityInStock = quantityInStock;
    return this;
  }

}
