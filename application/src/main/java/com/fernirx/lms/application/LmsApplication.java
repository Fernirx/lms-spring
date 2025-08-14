package com.fernirx.lms.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.fernirx.lms"})
@EnableJpaRepositories(basePackages = "com.fernirx.lms")
@EntityScan(basePackages = "com.fernirx.lms")
@ConfigurationPropertiesScan("com.fernirx.lms")
public class LmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsApplication.class, args);
    }

}
