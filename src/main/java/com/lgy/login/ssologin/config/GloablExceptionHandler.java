package com.lgy.login.ssologin.config;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *@autor
 *
 *@date 2021/4/26 16:15
 ****/
@ControllerAdvice
public class GloablExceptionHandler {
   @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e){
        String msg = e.getMessage();
        if(msg=="" || msg == null){
            msg ="服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message",msg);
        return jsonObject;
    }
}
