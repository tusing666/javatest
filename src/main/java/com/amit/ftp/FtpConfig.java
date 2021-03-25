package com.amit.ftp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class FtpConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Can be judged by os
        String os = System.getProperty("os.name");
        //linux settings
//        registry.addResourceHandler("/ftp/**").addResourceLocations("file:/home/pic/");
        //windows settings
        //The first method sets the access path prefix, and the second method sets the resource path. You can specify either the project classpath path or other non project paths
        registry.addResourceHandler("/ftp/**").addResourceLocations("file:D:\\apache-ftpserver-1.1.1\\res\\bxl-home\\");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}