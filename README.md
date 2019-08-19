# SpringMVC servlet 3.0
#一.官网文档:
```
https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/
```

#二.原理分析:
```
1.Web容器在启动的时候，会扫描每个jar包下的META-INF/services/javax.servlet.ServletContainerInitializer

2.加载这个文件指定的类org.springframework.web.SpringServletContainerInitializer

3.Tomcat启动后根据servlet 3.0约定，会加载注解指定的WebApplicationInitializer接口下的所有组件

4.为WebApplicationInitializer组件创建对象(组件不是接口，不是抽象类)

5.第一层抽象类：org.springframework.web.context.AbstractContextLoaderInitializer创建Root应用上下文

6.第二层抽象类：org.springframework.web.servlet.support.AbstractDispatcherServletInitializer创建Servlet应用上下文

7.第三层抽象类：AbstractAnnotationConfigDispatcherServletInitializer注解方式配置的初始化器

8.自定义启动SpringMVC,必须自己定义一个实现类,才能真的在应用容器上下文中创建SpringMVC组件
```


#三.定制SpringMVC开启配置及配置组件:
1.@EnableWebMvc开启SpringMVC定制配置功能,类似xml文件中下面的配置
```
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {}
```
类似原xml的如下配置
```
<mvc:annotation-driven/>
```

2.配置组件静态资源映射
```
@Override
public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
 // 相当于配置文件中的<mvc:default-servlet-handler/>,将spring处理不了的请求交给tomcat,静态资源就可以访问
    configurer.enable();
}
```
类似原xml的如下配置
```
<mvc:default-servlet-handler/>
```

3.配置组件视图解析器
```
public void configureViewResolvers(ViewResolverRegistry registry) {
    // jsp("/WEB-INF/", ".jsp");默认就是从/WEB-INF/下找.jsp文件
    registry.enableContentNegotiation(new MappingJackson2JsonView());
    registry.jsp();
//  registry.jsp("/WEB-INF/", ".html");
}
```
类似原xml的如下配置
```
<mvc:view-resolvers>
    <mvc:content-negotiation>
        <mvc:default-views>
            <bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
        </mvc:default-views>
    </mvc:content-negotiation>
    <mvc:jsp/>
</mvc:view-resolvers>
```

4.配置拦截器
```
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
}
```
类似原xml的如下配置
```
<mvc:interceptors>
    <bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor"/>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <mvc:exclude-mapping path="/admin/**"/>
        <bean class="org.springframework.web.servlet.theme.ThemeChangeInterceptor"/>
    </mvc:interceptor>
    <mvc:interceptor>
        <mvc:mapping path="/secure/*"/>
        <bean class="org.example.SecurityInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```

#四.总结：
```
1.搞清楚概念:
    应用容器上下文:web应用容器包括全部应用所需资源的大容器,比如tomcat的一个host中的一个web应用,或者
理解为ServletContext就是web应用容器
    Root应用上下文:存放应用业务逻辑Service和持久层类的容器
    Servlet应用上下文:存放Controller和请求映射等的容器

2.以注解方式来启动Spring MVC,只需要继承 AbstractAnnotationConfigDispatcherServletInitializer实现
抽象方法,指定DispatcherServlet的配置信息,就能启动SpringMVC组件创建

3.根据官网地址的配置,自定义配置Spring MVC,除了以上还可以配置视图映射,静态资源映射等等
```