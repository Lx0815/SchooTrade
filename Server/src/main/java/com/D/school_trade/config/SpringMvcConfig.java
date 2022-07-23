package com.D.school_trade.config;

import com.D.school_trade.web.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: Ding
 * @date: 2022/7/16
 * @description:
 * @modify:
 */
@Configuration
@ComponentScan("com.D.school_trade.web.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * Bean注解 实例化我们定义的拦截器交给spring管理
     * @return
     */
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    /**
     * 添加我们定义的拦截器到拦截器链
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(tokenInterceptor());
        interceptor.addPathPatterns("/**");

    }
}
