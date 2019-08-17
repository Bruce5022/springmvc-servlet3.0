# SpringMVC servlet 3.0
#一.官网文档
```
https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/
```

#二.原理分析
```
1.Web容器在启动的时候，会扫描每个jar包下的META-INF/services/javax.servlet.ServletContainerInitializer

2.加载这个文件指定的类ServletContainerInitializer

3.Spring的应用一启动，会加载感兴趣的WebApplicationInitializer接口下的所有组件

4.并且为WebApplicationInitializer组件创建对象(组件不是接口，不是抽象类)

5.第一个子类：org.springframework.web.context.AbstractContextLoaderInitializer创建跟容器createRootApplicationContext()

6.第二个子类：org.springframework.web.servlet.support.AbstractDispatcherServletInitializer继承了上一个类

7.第三个子类：AbstractAnnotationConfigDispatcherServletInitializer：注解方式配置的初始化器
```

#三.总结：

```
以注解方式来启动Spring MVC；继承 AbstractAnnotationConfigDispatcherServletInitializer实现抽象方法指定DispatcherServlet的配置信息
```

#四.定制SpringMVC
1.@EnableWebMvc开启SpringMVC定制配置功能,类似xml文件中下面的配置
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <mvc:annotation-driven/>

</beans>
```

2.配置组件(视图解析器,视图映射,静态资源映射,拦截器...)
```
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 相当于配置文件中的<mvc:default-servlet-handler/>,将spring处理不了的请求交给tomcat,静态资源就可以访问
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 配置视图解析器
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.jsp();
        
        
       // <mvc:view-resolvers>
       //     <mvc:content-negotiation>
       //         <mvc:default-views>
       //             <bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
       //         </mvc:default-views>
       //     </mvc:content-negotiation>
       //     <mvc:jsp/>
       // </mvc:view-resolvers>
        
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
```