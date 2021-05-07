package com.lgy.login.ssologin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.lgy.login.ssologin.mapper")
public class SsoLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoLoginApplication.class, args);
    }

}
