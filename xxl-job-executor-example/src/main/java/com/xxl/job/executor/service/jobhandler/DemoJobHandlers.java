package com.xxl.job.executor.service.jobhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xxl.job.core.handler.annotation.JobHander;

@Service
@JobHander("demoJobHandlers")
public class DemoJobHandlers {
    private static transient Logger logger = LoggerFactory.getLogger(DemoJobHandlers.class);
    
    public void test1() {
        logger.info("XXL-JOB, Hello World.我是 test1");
    }

    public void test2() {
        logger.info("XXL-JOB, Hello World.我是 test2");
    }

    public void test3() {
        logger.info("XXL-JOB, Hello World.我是 test3");
    }
}
