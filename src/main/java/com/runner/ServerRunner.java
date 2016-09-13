package com.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@ComponentScan({ "com" })
public class ServerRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerRunner.class, args);
    }
}
