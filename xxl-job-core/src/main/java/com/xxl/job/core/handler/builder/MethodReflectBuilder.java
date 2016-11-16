package com.xxl.job.core.handler.builder;

import java.lang.reflect.Method;

import com.xxl.job.core.handler.IJobHandler;

public class MethodReflectBuilder implements MethodBuilder {

    @Override
    public IJobHandler build(Method value,Object handler) {
        IJobHandler jobHandler = new IReflectJobHandler(value,handler);
        return jobHandler;
    }

}
