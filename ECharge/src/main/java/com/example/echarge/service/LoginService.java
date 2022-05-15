package com.example.echarge.service;

import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.UserEntity;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;


@Service
public class LoginService {

    @Autowired
    private UserDao userDao;
    private String appID = "wx24d42abf42402eef";
    private String appSecret = "2f5a0937d16321a23c7e95b0bfa78382";
    private RestTemplate restTemplate = new RestTemplate();
    public LinkedHashMap<String, Object> getUserInfo(String code)
    {
        System.out.println(code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + this.appID + "&secret="
                + this.appSecret + "&js_code="
                + code
                + "&grant_type=authorization_code";
        String resStr = restTemplate.getForObject(url, String.class);
        System.out.println(resStr);
        JSONParser json = new JSONParser(resStr);
        LinkedHashMap<String, Object> res;
        try {
            res = json.parseObject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String session = res.get("session_key").toString();
        String openid = res.get("openid").toString();
        System.out.println("res" + session+" "+openid);
//        数据库中查找用户
        UserEntity user = userDao.findByWxId(openid);
        if(user == null){
            res.put("login", false);
            System.out.println("no user");
        }
        else{
            res.put("login", true);
            res.put("uid", user.getUid());
            System.out.println("has user");
        }
        return res;
    }
}
