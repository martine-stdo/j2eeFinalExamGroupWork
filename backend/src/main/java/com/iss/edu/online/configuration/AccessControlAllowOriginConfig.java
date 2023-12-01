package com.iss.edu.online.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AccessControlAllowOriginConfig implements WebMvcConfigurer {
    // 配置跨域资源共享（CORS）的设置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径的跨域请求
        registry.addMapping("/**")
                // 允许所有来源的请求
                .allowedOriginPatterns("*")
                // 允许的请求方法：GET、POST、PUT、DELETE
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 允许发送认证信息（如 cookie）
                .allowCredentials(true)
                // 预检请求的缓存时间，单位秒
                .maxAge(3600)
                // 允许的请求头，* 表示允许所有
                .allowedHeaders("*");
    }
}
