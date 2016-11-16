package com.xxl.job.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxl.job.admin.util.RequestUtil;

@Controller
@RequestMapping("/excutor")
public class RegistryExcutorController {
    @RequestMapping("/registry")
    @ResponseBody
    public Map<String,Object> registry(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            String ipAddr = RequestUtil.getIpAddr(request);
            result.put("ip", ipAddr);
            System.out.println(ipAddr);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
        }
        return result;
    }
}
