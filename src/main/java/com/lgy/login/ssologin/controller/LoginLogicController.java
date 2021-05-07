package com.lgy.login.ssologin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lgy.login.ssologin.pojo.User;
import com.lgy.login.ssologin.service.UserService;
import com.lgy.login.ssologin.until.Until;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("loginApi")
public class LoginLogicController {
    @Autowired
    UserService userService;
    @Autowired
    JedisPool jedisPool;
    /**
     * 使用 redis 保存用户信息
     */
    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "username") String username, @RequestParam(value = "passwrold")String passwrold,
                          HttpSession session, HttpServletResponse response){
        Jedis jedis = jedisPool.getResource();
        try {
            List<Map<Object,Object>> list = userService.findUserByNameAndPasaWorld(username,passwrold);
            if(list.size()!=0){
                /**
                 * 将map 转换成string 类型的json 字符串
                 * 生成token
                 * 放入redis
                 *
                 */
                String json =JSONObject.toJSONString(list.get(0));
                User user = new User();
                user.setName(username);
                user.setPassworld(passwrold);
                String token = Until.getToken(user);
                /**
                 * token 放入 cookie
                 */
                Cookie cookie = new Cookie("TOKEN",token);
                cookie.setPath("/");
                cookie.setDomain("main.com");
                response.addCookie(cookie);
                System.out.println(cookie.getValue()+"==token");
                System.out.println(cookie.getDomain()+"==doMain");
                //生成token 放入redis
                jedis.set(token,json);
                jedis.expire(token,3000);
                System.out.println("response");
                System.out.println(response);
            }else{
                session.setAttribute("msg","用户名或密码错误");
                return "index" ;
            }
            String target = (String) session.getAttribute("target");
            return "redirect:" + target;
       }finally {
               jedis.close();
       }
    }
    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestParam String token){
        Jedis jedis = jedisPool.getResource();
        /**
         * 如果token 不为空 ，从redis 中取出用户信息，响应回去 用户信息
         * 反之 返回
         */
        try {
            if(!StringUtils.isEmpty(token)){
                jedis.get(token);
                User user = JSONObject.parseObject( jedis.get(token),User.class);

                return ResponseEntity.ok(user);
            }else{
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }finally {
            jedis.close();
        }


    }
    /**
     * 退出登录
     * @param cookie
     * @param response
     * @return
     */
    @RequestMapping("/logOut")
    public String loginOut(@RequestParam(value = "target") String target,@CookieValue(value = "TOKEN") Cookie cookie, HttpServletResponse response){
        System.out.println(target);
        Jedis jedis = jedisPool.getResource();
        cookie.setMaxAge(0);
        /**
         * 从 redis中删除当前内容
         */

        try {
            jedis.del(cookie.getValue());
        }finally {
            jedis.close();
        }
        response.addCookie(cookie);
        return "redirect:"+target;
    }

}
