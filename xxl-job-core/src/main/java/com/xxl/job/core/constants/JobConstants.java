package com.xxl.job.core.constants;

import org.springframework.beans.factory.InitializingBean;

public class JobConstants implements InitializingBean {
    public static String REGISTRY_URL = null;
    public static String ADMIN_FLAG_CLASS = null;
    public static int REQUEST_QUEUE_SIZE = 5000;
    
    private  String registryUrl = null;
    private  String adminFlagClass = null;
    private  String requestQueueSize = null;
    @Override
    public void afterPropertiesSet() throws Exception {
        REGISTRY_URL=registryUrl;
        ADMIN_FLAG_CLASS=adminFlagClass;
        if(requestQueueSize!=null&&requestQueueSize.trim().length()>0){
            REQUEST_QUEUE_SIZE = Integer.valueOf(requestQueueSize);
        }
    }

    public void setRegistryUrl(String registryUrl) {
        this.registryUrl = registryUrl;
    }

    public void setAdminFlagClass(String adminFlagClass) {
        this.adminFlagClass = adminFlagClass;
    }

    public String getRegistryUrl() {
        return registryUrl;
    }

    public String getAdminFlagClass() {
        return adminFlagClass;
    }

    public String getRequestQueueSize() {
        return requestQueueSize;
    }

    public void setRequestQueueSize(String requestQueueSize) {
        this.requestQueueSize = requestQueueSize;
    }
}
