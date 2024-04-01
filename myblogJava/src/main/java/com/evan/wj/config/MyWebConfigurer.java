package com.evan.wj.config;

import com.evan.wj.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/projects/毕业设计/workplace/img/");
//        registry.addResourceHandler("/api/userImg/**").addResourceLocations("file:" + "d:/projects/毕业设计/workplace/userImg/");
//        registry.addResourceHandler("/api/topicImg/**").addResourceLocations("file:" + "d:/projects/毕业设计/workplace/topicImg/");
//        registry.addResourceHandler("/api/noticeImg/**").addResourceLocations("file:" + "d:/projects/毕业设计/workplace/noticeImg/");
        registry.addResourceHandler("/api/file/blog/**").addResourceLocations("file:" + "d:/projects/blogFile/blog/");
        registry.addResourceHandler("/api/file/music/**").addResourceLocations("file:" + "d:/projects/blogFile/music/");
        registry.addResourceHandler("/api/file/musicImg/**").addResourceLocations("file:" + "d:/projects/blogFile/musicImg/");
        registry.addResourceHandler("/api/file/vlog/**").addResourceLocations("file:" + "d:/projects/blogFile/vlog/");
        registry.addResourceHandler("/api/file/vlogImg/**").addResourceLocations("file:" + "d:/projects/blogFile/vlogImg/");
        registry.addResourceHandler("/api/file/photoWidth/**").addResourceLocations("file:" + "d:/projects/blogFile/photoWidth/");
        registry.addResourceHandler("/api/file/photoHeight/**").addResourceLocations("file:" + "d:/projects/blogFile/photoHeight/");
    }


}

