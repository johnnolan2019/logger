package com.cit.micro.logger.service;

import com.cit.micro.logger.LoggerApplication;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GrpcListenerService {

    private static int port = 6565;
    final private static Logger logger = LoggerFactory.getLogger(LoggerApplication.class);

    public static void serverRun() {
        Server server = ServerBuilder
                .forPort(port)
                .addService(new GrpcLogger()).build();
        try {
            server.start();
            logger.info("The Logger Service is now running");
            server.awaitTermination();
        } catch (IOException e) {
            logger.error("Logger Service threw IO exception");
        } catch (InterruptedException e) {
            logger.error("Logger Service threw Interrupted exception");
        }
    }
}