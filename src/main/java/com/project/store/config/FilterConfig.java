package com.project.store.config;


import com.project.store.filter.CrosFilter;
import com.project.store.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean filterRegistrationBean1 = new FilterRegistrationBean();
        filterRegistrationBean1.setFilter(new UserFilter());
        filterRegistrationBean1.addUrlPatterns("/cart/*", "/orders/*", "/user/userInfo", "/userAddress/*", "/errand/*");
        filterRegistrationBean1.setOrder(1);
        return filterRegistrationBean1;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean filterRegistrationBean2 = new FilterRegistrationBean();
        filterRegistrationBean2.setFilter(new CrosFilter());
        filterRegistrationBean2.addUrlPatterns("*");
        filterRegistrationBean2.setOrder(0);
        return filterRegistrationBean2;
    }

}
