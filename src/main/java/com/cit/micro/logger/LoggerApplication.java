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
    final private static Logger LOGGER = LoggerFactory.getLogger(LoggerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
        Server server = ServerBuilder
                .forPort(6565)
                .addService(new GrpcLogger()).build();
        try{
            server.start();
            server.awaitTermination();
        }catch (IOException e){
            LOGGER.info("bad");
        }catch (InterruptedException e){
            LOGGER.error("Not as bad, but not good");
        }
    }

}
