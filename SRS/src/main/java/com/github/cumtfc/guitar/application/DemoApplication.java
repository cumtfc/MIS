package com.github.cumtfc.guitar.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@SpringBootApplication(scanBasePackages = {"com.github.cumtfc.guitar.controller", "com.github.cumtfc.guitar.service",
    "com.github.cumtfc.guitar.component"})
@EntityScan(basePackages = "com/github/cumtfc/guitar/po")
@EnableJpaRepositories(basePackages = "com.github.cumtfc.guitar.dao")
@EnableConfigurationProperties
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
