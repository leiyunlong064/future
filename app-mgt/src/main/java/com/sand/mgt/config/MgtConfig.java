package com.sand.mgt.config;

import com.sand.biz.config.BizConfig;
import com.sand.core.config.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.sand.mgt"})
@Import({BizConfig.class, CoreConfig.class})
public class MgtConfig {
}
