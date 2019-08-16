package com.sky.spingmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

// Spring的容器只扫描controller：子容器
@ComponentScan(value = "com.sky.spingmvc", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
}, useDefaultFilters = false)
public class AppConfig {
}
