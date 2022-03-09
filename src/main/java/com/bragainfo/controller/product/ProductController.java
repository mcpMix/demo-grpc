package com.bragainfo.controller.product;

import com.bragainfo.EmptyReq;
import com.bragainfo.Id;
import com.bragainfo.ProductReq;
import com.bragainfo.ProductRes;
import com.bragainfo.ProductRespList;
import com.bragainfo.ProductServiceGrpc;
import com.bragainfo.domain.dto.ProductRequestDTO;
import com.bragainfo.domain.dto.ProductResponseDTO;
import com.bragainfo.service.ProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public void create(ProductReq request, StreamObserver<ProductRes> responseObserver) {
    LOGGER.info("stage=init method=ProductController.create message= Begin create product request={}", request.getName());

    ProductRequestDTO productDTO = new ProductRequestDTO()
                  .withName(request.getName())
                  .withPrice(request.getPrice())
                  .withQuantityInStock(request.getQuantityInStock());

    ProductResponseDTO productResponseDTO = productService.create(productDTO);

    ProductRes response = ProductRes.newBuilder()
        .setId(productResponseDTO.getId())
        .setName(productResponseDTO.getName())
        .setPrice(productResponseDTO.getPrice())
        .setQuantityInStock(productResponseDTO.getQuantityInStock())
        .setCreateAt(productResponseDTO.getCreateAt())
        .setUpdatedAt(productResponseDTO.getUpdateAt())
        .setVersion(productResponseDTO.getVersion())
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
    LOGGER.info("stage=end method=ProductController.create message= Finish create product response={} ");
  }

  @Override
  public void findById(Id request, StreamObserver<ProductRes> responseObserver) {
    LOGGER.info("stage=init method=ProductController.findById message= Begin find by id={}", request.getId());
    ProductResponseDTO productResponseDTO = productService.findById(request.getId());

    ProductRes response = ProductRes.newBuilder()
        .setId(productResponseDTO.getId())
        .setName(productResponseDTO.getName())
        .setPrice(productResponseDTO.getPrice())
        .setQuantityInStock(productResponseDTO.getQuantityInStock())
        .setCreateAt(productResponseDTO.getCreateAt())
        .setUpdatedAt(productResponseDTO.getUpdateAt())
        .setVersion(productResponseDTO.getVersion())
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();

    LOGGER.info("stage=end method=ProductController.findById message= Finish find by product");
  }

  @Override
  public void delete(Id request, StreamObserver<ProductRes> responseObserver) {
    super.delete(request, responseObserver);
  }

  @Override
  public void findAll(EmptyReq request, StreamObserver<ProductRespList> responseObserver) {
    super.findAll(request, responseObserver);
  }
}
