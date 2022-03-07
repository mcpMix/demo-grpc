package com.bragainfo.controller;


import com.bragainfo.HelloReq;
import com.bragainfo.HelloRes;
import com.bragainfo.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class HelloController extends HelloServiceGrpc.HelloServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Override
    public void hello(HelloReq request, StreamObserver<HelloRes> responseObserver) {
      LOGGER.info("stage=init method=HelloController.hello message= Begin hello world request={}", request.getMessage());
            var response = HelloRes.newBuilder()
                    .setMessage(request.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();


      LOGGER.info("stage=end method=HelloController.hello message= Finish hello world response={} ", response.getMessage());

    }
}
