package com.ftd.seckill.ftdservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.ftd.seckill.ftdservice.mapper")
public class FtdWebConfiguration {
    // 自定义MVC配置组件
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer(){
            @Override
            /**
             * 根目录主页访问转发
             */
            public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main").setViewName("index");
            }
        };
    }
}
