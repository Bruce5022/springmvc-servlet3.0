package com.sky.springmvc.config;

import com.sky.springmvc.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

// Spring的容器只扫描controller：子容器
@ComponentScan(value = "com.sky.springmvc", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
}, useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // jsp("/WEB-INF/", ".jsp");默认就是从/WEB-INF/下找.jsp文件
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.jsp();
//        registry.jsp("/WEB-INF/", ".html");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
    }
}
