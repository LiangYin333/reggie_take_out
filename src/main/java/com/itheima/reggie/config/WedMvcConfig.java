package com.itheima.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 键盘敲烂,工资过万.
 */
@Slf4j
@Configuration
public class WedMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
    @Override
    */
   protected void addResourceHandlers(ResourceHandlerRegistry registry){
       log.info("开始静态资源映射~~~~~");
registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
       registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
   }



}
