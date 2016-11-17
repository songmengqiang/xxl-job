package com.xxl.job.executor.service.jobhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xxl.job.core.handler.annotation.JobHander;

/**
 * 此时test2 test3 将不会被解析为执行器
 * @author 宋梦强
 * @version $Id: DemoJobHandlers2.java, v 0.1 2016年11月17日 上午9:45:59 bb188641864 Exp $
 */
@Service
@JobHander("demoJobHandlers2")
public class DemoJobHandlers2 {
    private static transient Logger logger = LoggerFactory.getLogger(DemoJobHandlers2.class);
    
    @JobHander
    public void test1() {
        logger.info("demoJobHandlers2 XXL-JOB, Hello World.我是 test1");
    }

    public void test2() {
        logger.info("demoJobHandlers2 XXL-JOB, Hello World.我是 test2");
    }

    public void test3() {
        logger.info("demoJobHandlers2 XXL-JOB, Hello World.我是 test3");
    }
}
