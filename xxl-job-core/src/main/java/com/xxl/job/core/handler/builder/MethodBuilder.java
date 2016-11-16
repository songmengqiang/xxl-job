package com.xxl.job.core.handler.builder;

import java.lang.reflect.Method;

import com.xxl.job.core.handler.IJobHandler;

public interface MethodBuilder {

    IJobHandler build(Method value, Object handler);

}
