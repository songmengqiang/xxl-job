package com.xxl.job.core.util.ip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpGetJavaUtilChain implements IpGetChain {
    @Override
    public String getIp() {
        List<String> ips = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress address = null;
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && address.getHostAddress().indexOf(":") == -1) {
                        ips.add(address.getHostAddress());
                    }
                }
            }
        } catch (Throwable t) {
            return null;
        }if(ips.size()>1){
            return ips.get(0);  //ips.size()可能大于1
        }else{
            return null;
        }
    }
}
