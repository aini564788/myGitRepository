package com.lmh.service.impl;

import com.lmh.ssm.HttpClientUtils;
import com.lmh.ssm.JsonUtils;
import com.lmh.ssm.MD5Utils;
import com.lmh.utils.APIUrlUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl {

    public Object userLogin(String uname,String pwd){
        Map<String,String> map=new HashMap<>();
        map.put("uname",uname);
        map.put("pwd", MD5Utils.encrypt(pwd));
        String s = HttpClientUtils.doGet(APIUrlUtils.LOGIN, map);
        if(s!=null){
            return JsonUtils.jsonToList(s,null);
        }
        return null;

    }



}
