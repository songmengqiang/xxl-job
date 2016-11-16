package com.xxl.job.core.handler.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation for job handler
 * @author 2016-5-17 21:06:49
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JobHanderIgnore {

    String value() default "";
    
}
