package com.bragainfo.exception.handler;

import com.bragainfo.exception.BaseBusinessException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class HandlerException {

  @GrpcExceptionHandler(BaseBusinessException.class)
  public StatusRuntimeException handleBusinessException(BaseBusinessException exp){
    return exp.getStatusCode()
        .withCause(exp.getCause())
        .withDescription(exp.getErrorMessage())
        .asRuntimeException();
  }


}
