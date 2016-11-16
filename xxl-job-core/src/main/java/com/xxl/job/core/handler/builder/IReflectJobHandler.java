package com.xxl.job.core.handler.builder;

import java.lang.reflect.Method;

import com.xxl.job.core.handler.IJobHandler;

public class IReflectJobHandler extends IJobHandler {
    private Method targetMethod;
    private Object bean;
    public IReflectJobHandler(Method targetMethod,Object bean){
        this.targetMethod = targetMethod;
        this.bean = bean;
    }
    @Override
    public void execute(String... params) throws Exception {
        //TODO 参数处理
        targetMethod.invoke(bean);
    }
}
