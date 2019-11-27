package com.sand.job.impl;

import com.sand.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJob extends BaseJob {
    private final Logger logger = LoggerFactory.getLogger(TestJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("TestJob start");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("testJob end");
    }
}
