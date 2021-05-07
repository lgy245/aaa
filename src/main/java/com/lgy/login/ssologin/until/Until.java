package com.lgy.login.ssologin.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lgy.login.ssologin.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *@autor lgy
 *
 *@date 2021/4/8 17:34
 ****/
public  class Until {
    public static int MathRodem(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        int min =1000,max=9999;
        return (int)(min+Math.random()*max) + Integer.valueOf(sdf.format(date));
    }
    /**
     * token 生成方法
     */
    public static String getToken(User user){
        String token ="";
        //创建token
        token = JWT.create()
                .withAudience(user.getName())// 存入需要保存在token 的信息，这里存的是 用户名
                .withExpiresAt(new Date(System.currentTimeMillis()+30*1000))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(user.getPassworld()));// 这里把 用户的密码用 HMAC256 方法加密之后 为token的 密匙
        return token;
    }
}
