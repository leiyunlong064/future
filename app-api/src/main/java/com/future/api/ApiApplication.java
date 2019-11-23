package com.future.api;

import com.future.api.config.ApiConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-23
 **/
@Configuration
@EnableAutoConfiguration
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiConfig.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("System is running!");
    }

}
