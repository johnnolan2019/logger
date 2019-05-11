package com.cit.micro.logger;

import com.cit.micro.logger.service.GrpcListenerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
        GrpcListenerService.serverRun();
    }

}
