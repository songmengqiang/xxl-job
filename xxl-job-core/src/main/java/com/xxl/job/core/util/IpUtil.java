package com.xxl.job.core.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.job.core.util.ip.IpGetChain;
import com.xxl.job.core.util.ip.IpGetFromAdminChain;
import com.xxl.job.core.util.ip.IpGetJavaUtilChain;

/**
 * get ip
 * @author xuxueli 2016-5-22 11:38:05
 */
public class IpUtil {
    private static final Logger logger      = LoggerFactory.getLogger(IpUtil.class);
    private static IpUtil       singleten;
    private List<IpGetChain>    ipGetChains = new ArrayList<IpGetChain>();

    private IpUtil() {
        ipGetChains.add(new IpGetFromAdminChain());
        ipGetChains.add(new IpGetJavaUtilChain());
    }

    private String doGetIp() {
        String ip = null;
        for (IpGetChain ipGetChain : ipGetChains) {
            ip = ipGetChain.getIp();
            if(ip!=null){
                break;
            }
        }
        if (ip == null) {
            logger.error("can not find ip address !");
        } else {
            return ip;
        }
        return null;
    }

    /**
     * 获取本机ip
     * @return ip
     * @throws Exception 
     */
    public static String getIp() throws Exception {
        if (singleten == null) {
            singleten = new IpUtil();
        }
        String ip = singleten.doGetIp();
        if(ip==null||ip.length()==0){
            throw new Exception("can not find ip address !");
        }
        logger.debug("find local address :"+ip);
        return ip;
    }
}
