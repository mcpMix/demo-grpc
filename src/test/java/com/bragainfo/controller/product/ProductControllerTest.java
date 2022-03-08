package com.bragainfo.controller.product;

import com.bragainfo.ProductReq;
import com.bragainfo.ProductRes;
import com.bragainfo.ProductServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class ProductControllerTest {

  @GrpcClient("inProcess")
  private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

  @Autowired
  private Flyway flyway;

  @BeforeEach
  private void setUp(){
    flyway.clean();
    flyway.migrate();
  }

  @Test
  public void createProductSuccessTest(){
    ProductReq productReq =  ProductReq.newBuilder()
        .setName("Cerveja")
        .setPrice(200.00)
        .setQuantityInStock(20)
        .build();

    ProductRes productRes = serviceBlockingStub.create(productReq);

    Assertions.assertThat(productReq)
        .usingRecursiveComparison()
        .comparingOnlyFields("name","price","quantity_in_stock")
        .isEqualTo(productRes);

  }

  @Test
  public void createProductAlreadyExistsExceptionTest(){
    ProductReq productReq =  ProductReq.newBuilder()
        .setName("Product A")
        .setPrice(200.00)
        .setQuantityInStock(20)
        .build();


    Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
        .isThrownBy(()->serviceBlockingStub.create(productReq))
        .withMessage("ALREADY_EXISTS: Product Product A already exists.");

  }

}
