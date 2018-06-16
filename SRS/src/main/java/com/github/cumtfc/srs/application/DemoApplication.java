package com.github.cumtfc.srs.application;

import com.github.cumtfc.srs.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@SpringBootApplication(scanBasePackages = {"com.github.cumtfc.srs.controller", "com.github.cumtfc.srs.service",
    "com.github.cumtfc.srs.component","com.github.cumtfc.srs.interceptor"})
@EntityScan(basePackages = "com.github.cumtfc.srs.po")
@EnableJpaRepositories(basePackages = "com.github.cumtfc.srs.dao")
@EnableConfigurationProperties
public class DemoApplication implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
}
