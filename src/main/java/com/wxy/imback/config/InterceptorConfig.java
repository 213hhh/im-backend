package com.wxy.imback.config;

import com.wxy.imback.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {


    // TODO: 2023/5/21 优化拦截器 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/*")
                .excludePathPatterns(
                        "/user/mail_register",
                        "/user/mail_login",
                        "/user/captcha",
                        "/user/send_code");
    }
}
