package com.grapplesoft.meil_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.grapplesoft.meil_backend.configuration", "com.grapplesoft.meil_backend.controllers", "com.grapplesoft.meil_backend.models", "com.grapplesoft.meil_backend.repositories", "com.grapplesoft.meil_backend.security", "com.grapplesoft.meil_backend.services"})
public class MeilBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeilBackendApplication.class, args);
    }

}
