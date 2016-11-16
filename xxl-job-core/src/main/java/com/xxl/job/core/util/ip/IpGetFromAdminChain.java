package com.xxl.job.core.util.ip;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.job.core.constants.JobConstants;
import com.xxl.job.core.util.IpUtil;
import com.xxl.job.core.util.JacksonUtil;

public class IpGetFromAdminChain implements IpGetChain {
    private static final Logger logger      = LoggerFactory.getLogger(IpGetFromAdminChain.class);
    private static Class<?> adminFlagClass=null;
    
    static{
        try {
            adminFlagClass = Class.forName("com.xxl.job.admin.controller.RegistryExcutorController");
        } catch (ClassNotFoundException e) {
            //没有发现则任务是执行器端
        }
    }
    
    @Override
    public String getIp() {
        if(adminFlagClass!=null){
            return null;
        }
        return getFromAdmin();
    }

    @SuppressWarnings("rawtypes")
    private String getFromAdmin() {
        // jar包被引入执行器端
        CloseableHttpClient httpClient = HttpClients.custom().disableAutomaticRetries().build();
        if(JobConstants.REGISTRY_URL==null||JobConstants.REGISTRY_URL.length()==0){
            return null;
        }
        HttpPost httpPost = new HttpPost(JobConstants.REGISTRY_URL);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200 && null != entity) {
                String resp = EntityUtils.toString(entity, "UTF-8");
                Map readValue = JacksonUtil.readValue(resp, Map.class);
                if(true==(Boolean) readValue.get("isSuccess")){
                    String localIp = (String) readValue.get("ip");
                    return localIp;
                }
            }
        } catch (ClientProtocolException e) {
//            e.printStackTrace();
            logger.debug(e.getMessage());
        } catch (IOException e) {
//            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        return null;
    }

}
