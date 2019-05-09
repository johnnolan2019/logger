package com.cit.micro.logger;

import com.cit.micro.logger.service.GrpcLogger;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LoggerApplication {

    final private static Logger logger = LoggerFactory.getLogger(LoggerApplication.class);
    final private static int port = 6565;

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
        Server server = ServerBuilder
                .forPort(port)
                .addService(new GrpcLogger()).build();
        try{
            server.start();
            server.awaitTermination();
        }catch (IOException e){
            logger.error("Manager Service threw IO exception");
        }catch (InterruptedException e){
            logger.error("Manager Service threw Interrupted exception");
        }
    }

}
