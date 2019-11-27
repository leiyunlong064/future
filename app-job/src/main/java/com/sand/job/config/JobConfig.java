package com.sand.job.config;

import com.sand.biz.config.BizConfig;
import com.sand.core.config.CoreConfig;
import com.sand.job.impl.TestJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Import({BizConfig.class, CoreConfig.class})
@ComponentScan({"com.sand.job"})
@PropertySources({
        @PropertySource(value = "classpath:/job/job.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file://${CONFIG_HOME}/job.properties", ignoreResourceNotFound = true)
})
public class JobConfig {
    @Bean
    public JobDetail testJob(){
        return JobBuilder.newJob(TestJob.class)
                .withIdentity("testJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testJobTrigger(@Value("${test.corn.expression}") String corn){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
        return TriggerBuilder.newTrigger()
                .forJob(testJob())
                .withIdentity("testJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
