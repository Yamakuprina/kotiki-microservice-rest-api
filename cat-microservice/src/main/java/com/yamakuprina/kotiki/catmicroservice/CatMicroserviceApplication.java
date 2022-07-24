package com.yamakuprina.kotiki.catmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "entities")
@SpringBootApplication
public class CatMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatMicroserviceApplication.class, args);
    }

}
