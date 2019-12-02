package com.sand.api.config;

import com.sand.biz.config.BizConfig;
import com.sand.core.config.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-23
 **/
@Configuration
@ComponentScan(basePackages = "com.sand.api")
@Import({BizConfig.class, CoreConfig.class})
public class ApiConfig {

}