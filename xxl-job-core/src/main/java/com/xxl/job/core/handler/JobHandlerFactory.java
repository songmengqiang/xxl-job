package com.xxl.job.core.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.handler.builder.JobEntry;
import com.xxl.job.core.handler.builder.MissInterfaceCreator;

public class JobHandlerFactory {
    
    public static Map<String, Object> getHandlerMapBean(Map<String, Object> beansWithAnnotation) throws Exception {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        for (String key : beansWithAnnotation.keySet()) {
            Object handler = beansWithAnnotation.get(key);
            String jvalue = handler.getClass().getAnnotation(JobHander.class).value();
            key = jvalue!=null&&jvalue.trim()!=""?jvalue:key;
            if(handler instanceof IJobHandler){
                //如果bean实现了IJobHandler接口
                resultMap.put(key, handler);
            }else{
                //如果没有继承，则需要构建相关的IJobHandler接口
                MissInterfaceCreator missJobHandlerBuilder = new MissInterfaceCreator();
                List<JobEntry<String, IJobHandler>> mapEntrys = missJobHandlerBuilder.build(key,handler);
                for (JobEntry<String, IJobHandler> jobEntry : mapEntrys) {
                    if(resultMap.get(jobEntry.getKey())!=null){
                        throw new Exception("存在同名的执行器{},方法重载时需要使用 @JobHander 注解设置不同的名称"+jobEntry.getKey());
                    };
                    resultMap.put(jobEntry.getKey(),jobEntry.getValue());
                }
            }
        }
        return resultMap;
    }
}
