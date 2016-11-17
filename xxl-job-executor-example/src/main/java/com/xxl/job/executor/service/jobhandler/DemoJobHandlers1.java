package com.xxl.job.executor.service.jobhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xxl.job.core.handler.annotation.JobHander;

/**
 * 
 * @author 宋梦强
 * @version $Id: DemoJobHandlers1.java, v 0.1 2016年11月17日 上午9:48:42 bb188641864 Exp $
 */
@Service
@JobHander("demoJobHandlers1")
public class DemoJobHandlers1 {
    private static transient Logger logger = LoggerFactory.getLogger(DemoJobHandlers1.class);
    
    public void test1() {
        logger.info("demoJobHandlers1 XXL-JOB, Hello World.我是 test1");
    }

    public void test2() {
        logger.info("demoJobHandlers1 XXL-JOB, Hello World.我是 test2");
    }

    public void test3() {
        logger.info("demoJobHandlers1 XXL-JOB, Hello World.我是 test3");
    }
}
