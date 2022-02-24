package com.bragainfo.controller;


import com.bragainfo.HelloReq;
import com.bragainfo.HelloRes;
import com.bragainfo.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloController extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloReq request, StreamObserver<HelloRes> responseObserver) {
            var response = HelloRes.newBuilder()
                    .setMessage(request.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

    }
}
