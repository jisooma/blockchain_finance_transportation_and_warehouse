package org.fisco.bcos.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mazhixiu
 * @date 2021/4/7 10:03
 * @Email:861359297@qq.com
 */
/**
 * 开启 mvc支持，设置 static 目录为类路径
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 得到 classpath 的根路径， resources 目录下的所以路径都可以得到
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}