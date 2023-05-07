package com.rs.retailstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class RetailStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailStoreApplication.class, args);
    }

}
