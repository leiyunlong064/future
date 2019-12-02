package com.sand.biz.config;

import com.sand.core.config.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.sand.biz"})
@Import(CoreConfig.class)
public class BizConfig {
}
