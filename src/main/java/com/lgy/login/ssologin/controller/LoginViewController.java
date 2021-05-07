package com.lgy.login.ssologin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lgy.login.ssologin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginViewController {
    @Autowired
    JedisPool jedisPool;
    @RequestMapping("index")
    public String toIndex(@RequestParam(value = "target",required = false,defaultValue = "") String target,
                          HttpSession session, @CookieValue(required = false,value = "TOKEN")Cookie cookie){
        Jedis jedis = jedisPool.getResource();
        System.out.println(cookie+"==cookie");
        /**
         * 判断当前地址是否存在
         * 空 跳转首页，
         */
        System.out.println(target);
        if(StringUtils.isEmpty(target)){
            target ="http://www.main.com:8099";
        }

        /**
         * 判断用户是否已经登录
         * 已经登录 跳转首页
         */
        try {
            if(cookie!=null){
                String token = cookie.getValue();
               String userJson =  jedis.get(token);
                System.out.println(userJson);
               if(userJson!=null){
                   return "redirect:"+target;
               }
            }
        }finally {
            jedis.close();
        }
        session.setAttribute("target",target);
        session.removeAttribute("msg");
        return "index";
    }
}
