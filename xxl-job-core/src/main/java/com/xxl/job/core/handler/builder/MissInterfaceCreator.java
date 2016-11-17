package com.xxl.job.core.handler.builder;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.handler.annotation.JobHanderIgnore;

/**
 * 未实现IJbHandler接口时，将有该类构建处理类 
 * 实现目标，组装多个IJobHandler的实现类，调用处理类中的特定方法
 * 实现逻辑，如果bean对应的类上面写有注解@JobHander，并且未实现IJobHandler接口 则认为是需要处理的类
 * 
 * 备用构造执行器的方法必须是公有的
 * 
 * 方法上面可以写上注解@JobHander 或者@JobHander 或者不写
 * 
 * 如果存在方法上写有@JobHander注解的话，则其他方法都不会用于构造执行器
 * 
 * 如果公有方法上都不存在@JobHander注解的话，则所有不写有@JobHanderIgnore注解的公有方法将都会被构造成执行器
 * 
 * @author 宋梦强
 * @version $Id: MissJobHandlerBuilder.java, v 0.1 2016年11月14日 下午1:25:12 bb188641864 Exp $
 */
public class MissInterfaceCreator {
    private Object handler;
    private String key;
    public List<JobEntry<String, IJobHandler>> build(String key,Object handler) {
        this.key = key;
        this.handler = handler;
        List<Method> allPublicMethods = findAllPublicMethod(handler);
        List<JobEntry<JobHander,Method>> methodEntry = findMethodHaveAnno(allPublicMethods);
        List<JobEntry<String,IJobHandler>> handerEntrys = new ArrayList<JobEntry<String,IJobHandler>>();
        if(methodEntry.isEmpty()){
            handerEntrys = buildAllMethods(allPublicMethods);
        }else{
            handerEntrys = buildMethods(methodEntry);
        }
        return handerEntrys;
    }

    private List<Method> findAllPublicMethod(Object handler) {
        List<Method> results = new ArrayList<Method>();
        Method[] methods = handler.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if(method.getModifiers()==Modifier.PUBLIC){
                    results.add(method);
            }
        }
        return results;
    }

    private List<JobEntry<String, IJobHandler>> buildAllMethods(List<Method> allPublicMethods) {
        List<JobEntry<String, IJobHandler>> results= new ArrayList<JobEntry<String, IJobHandler>>();
        for (Method method : allPublicMethods) {
            if(method.getAnnotation(JobHanderIgnore.class)!=null){//如果方法上有JobHanderIgnore注解，则忽略该方法
                continue;
            }
            JobEntry<JobHander, Method> jobEntry = getEntryWithAnno(method);
            if(jobEntry==null){
                results.add(buildMethod(method,method.getName()));
            }else{
                results.add(buildMethod(jobEntry));
            }
        }
        return results;
    }
    
    private List<JobEntry<String, IJobHandler>> buildMethods(List<JobEntry<JobHander, Method>> methodEntry) {
        List<JobEntry<String, IJobHandler>> results= new ArrayList<JobEntry<String, IJobHandler>>();
        for (JobEntry<JobHander, Method> jobEntry : methodEntry) {
            results.add(buildMethod(jobEntry));
        }
        return results;
    }
//    ClassMethodJobHanderBuilder builder = new ClassMethodJobHanderJavasistBuilder();
    MethodBuilder builder = new MethodReflectBuilder();
    private JobEntry<String,IJobHandler> buildMethod(JobEntry<JobHander, Method> jobEntry) {
        Method vaue = jobEntry.getValue();
        String key =  jobEntry.getKey().value().equals("")?vaue.getName():jobEntry.getKey().value();
        return buildMethod(vaue, key);
    }

    private JobEntry<String, IJobHandler> buildMethod(Method vaue, String key) {
        key = this.key+"."+key;
        IJobHandler jobHanlder = builder.build(vaue,handler);
        return new JobEntry<String,IJobHandler>(key,jobHanlder);
    }
    private List<JobEntry<JobHander, Method>> findMethodHaveAnno(List<Method> methods) {
        List<JobEntry<JobHander, Method>> results = new ArrayList<JobEntry<JobHander, Method>>();
        for (Method method : methods) {
            JobEntry<JobHander, Method> jobEntry = getEntryWithAnno(method);
            if(jobEntry!=null){
                results.add(jobEntry);
            }
        }
        return results;
    }

    private JobEntry<JobHander, Method> getEntryWithAnno(Method method) {
        JobHander annotation = method.getAnnotation(JobHander.class);
      if(annotation!=null){
       return new JobEntry<JobHander, Method>(annotation,method);
      }
      return null;
    }
}
