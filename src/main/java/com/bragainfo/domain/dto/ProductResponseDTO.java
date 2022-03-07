package com.bragainfo.domain.dto;

public class ProductResponseDTO extends Response{

  private Long id;
  private String name;
  private Double price;
  private Integer  quantityInStock;
  private String createAt;
  private String updateAt;
  private Integer version;

  public Long getId() {
    return id;
  }

  public ProductResponseDTO withId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ProductResponseDTO withName(String name) {
    this.name = name;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public ProductResponseDTO withPrice(Double price) {
    this.price = price;
    return this;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public ProductResponseDTO withQuantityInStock(Integer quantityInStock) {
    this.quantityInStock = quantityInStock;
    return this;
  }

  public String getCreateAt() {
    return createAt;
  }

  public ProductResponseDTO withCreateAt(String createAt) {
    this.createAt = createAt;
    return this;
  }

  public String getUpdateAt() {
    return updateAt;
  }

  public ProductResponseDTO withUpdateAt(String updateAt) {
    this.updateAt = updateAt;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public ProductResponseDTO withVersion(Integer version) {
    this.version = version;
    return this;
  }
}
