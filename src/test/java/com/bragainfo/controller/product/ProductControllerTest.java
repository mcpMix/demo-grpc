package com.bragainfo.controller.product;

import static org.assertj.core.groups.Tuple.tuple;

import com.bragainfo.EmptyReq;
import com.bragainfo.Id;
import com.bragainfo.ProductReq;
import com.bragainfo.ProductRes;
import com.bragainfo.ProductRespList;
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
    ProductReq productReq = loadProductReqNewMock();

    ProductRes productRes = serviceBlockingStub.create(productReq);

    Assertions.assertThat(productReq)
        .usingRecursiveComparison()
        .comparingOnlyFields("name","price","quantity_in_stock")
        .isEqualTo(productRes);

  }

  @Test
  public void findByIdProductSuccessTest(){
    Id id = Id.newBuilder().setId(1L).build();

    ProductReq productReq = loadProductReqMock();

    ProductRes productRes = serviceBlockingStub.findById(id);

    Assertions.assertThat(productReq)
        .usingRecursiveComparison()
        .comparingOnlyFields("name","price","quantity_in_stock")
        .isEqualTo(productRes);

  }

  @Test
  public void createProductAlreadyExistsExceptionTest(){
    ProductReq productReq = loadProductReqMock();

    Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
        .isThrownBy(()->serviceBlockingStub.create(productReq))
        .withMessage("ALREADY_EXISTS: Product Product A already exists.");

  }

  @Test
  public void findByIdProductNotFoundExceptionTest(){
    Id id = Id.newBuilder().setId(5L).build();

    Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
        .isThrownBy(()->serviceBlockingStub.findById(id))
        .withMessage("NOT_FOUND: Product id=5 not found");

  }

  @Test
  public void deleteByIdSuccessTest(){
    Id id = Id.newBuilder().setId(1L).build();

    Assertions.assertThatNoException().isThrownBy(()->serviceBlockingStub.delete(id));
  }

  @Test
  public void deleteByIdProductNotFoundExceptionTest(){
    Id id = Id.newBuilder().setId(200L).build();

    Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
        .isThrownBy(()->serviceBlockingStub.delete(id))
        .withMessage("NOT_FOUND: Product id=200 not found");

  }

  @Test
  public void findAllProductSuccessTest(){
    EmptyReq empty = EmptyReq.newBuilder().build();

    ProductRespList productRes = serviceBlockingStub.findAll(empty);

    //Use only by example
    Assertions.assertThat(productRes).isInstanceOf(ProductRespList.class);

    Assertions.assertThat(productRes.getProductsCount()).isEqualTo(2);

    Assertions.assertThat(productRes.getProductsList())
        .extracting("id","name", "price","quantityInStock")
        .contains(
            tuple(1L,"Product A", 10.99, 10),
            tuple(2L,"Product B", 10.99, 10)
        );

  }


  private ProductReq loadProductReqMock() {
    ProductReq productReq =  ProductReq.newBuilder()
        .setName("Product A")
        .setPrice(10.99)
        .setQuantityInStock(10)
        .build();
    return productReq;
  }

  private ProductReq loadProductReqNewMock() {
    ProductReq productReq =  ProductReq.newBuilder()
        .setName("Product NEW")
        .setPrice(10.99)
        .setQuantityInStock(10)
        .build();
    return productReq;
  }

}
