package com.itheima.reggie.config;

import com.itheima.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

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

    /**
     * 扩展mvc的信息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("zhq");
        //创建信息转换器
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        //设置对象转换器，底层使用Jackson将java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());

        //将什么的信息转换器对象追加到mvc框架转换器集合中
        converters.add(0,messageConverter);

    }
}
