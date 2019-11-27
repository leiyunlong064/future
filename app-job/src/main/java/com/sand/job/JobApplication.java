package com.sand.job;

import com.sand.job.config.JobConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class JobApplication {
    public static void main(String[] args){
        SpringApplication application = new SpringApplication(JobConfig.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        System.out.println("app-job is running");
    }
}
