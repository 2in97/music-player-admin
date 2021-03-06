package com.test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.interceptor.AuthInterceptor;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor initAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initAuthInterceptor())
				/*
				 * .addPathPatterns("/admin/**") .addPathPatterns("/song/**")
				 * .addPathPatterns("/file/**")
				 */
        .excludePathPatterns("/admin/login");
    }

}
