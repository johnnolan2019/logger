package com.cit.micro.logger.service;

import com.cit.micro.logger.LoggerGrpc;
import com.cit.micro.logger.RemoteLog;
import com.cit.micro.logger.ReturnBool;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GrpcLogger extends LoggerGrpc.LoggerImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcLogger.class);

    @Override
    public void info (RemoteLog remoteLog, StreamObserver<ReturnBool> responseObserver){
        LOGGER.info(remoteLog.getServiceName() + ": " + remoteLog.getTimeStamp() + ": " +  remoteLog.getMessage());
        ReturnBool response = ReturnBool.newBuilder().setResult(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void error (RemoteLog remoteLog, StreamObserver<ReturnBool> responseObserver){
        LOGGER.error(remoteLog.getServiceName() + ": " + remoteLog.getTimeStamp() + ": " +  remoteLog.getMessage());
        ReturnBool response = ReturnBool.newBuilder().setResult(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void debug (RemoteLog remoteLog, StreamObserver<ReturnBool> responseObserver){
        LOGGER.debug(remoteLog.getServiceName() + ": " + remoteLog.getTimeStamp() + ": " +  remoteLog.getMessage());
        ReturnBool response = ReturnBool.newBuilder().setResult(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}