package com.lgy.login.ssologin.config;


import com.lgy.login.ssologin.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 *@autor
 *
 *@date 2021/4/26 16:04
 ****/
@Configuration//自定义配置类 加入ioc 容器
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    /**
     * 添加拦截器，设置拦截器过滤路径
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/loginApi/**");// 拦截登录请求, 判断是否有@LoginRequired 注解，决定是否需要登录
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }


}
