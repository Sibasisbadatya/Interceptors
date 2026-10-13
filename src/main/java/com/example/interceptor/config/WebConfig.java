package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthorizationInterceptor;
import com.example.interceptor.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public LoggingInterceptor loggingInterceptor;
    public AuthorizationInterceptor authorizationInterceptor;
    WebConfig(LoggingInterceptor loggingInterceptor){
        this.loggingInterceptor = loggingInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loggingInterceptor)
                .order(1);

        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/auth/login")
                .order(2);
    }
}
