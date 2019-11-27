package com.sand.mgt;

import com.sand.mgt.config.MgtConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class MgtApplication {
    public static void main(String[] args){
        SpringApplication application = new SpringApplication(MgtConfig.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        System.out.println("app-mgt is running");
    }
}
