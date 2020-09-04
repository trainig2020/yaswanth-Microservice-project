package com.springboot.microservice.config;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import com.springboot.microservice.interceptor.ViewService_Dept_EmpInterceptor;

 
@Configuration
public class LangConfiguration implements WebMvcConfigurer {
    
    @Autowired
    ViewService_Dept_EmpInterceptor deptEmpInter;
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:departmentmessage");
        
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver lr = new CookieLocaleResolver();
        lr.setDefaultLocale(Locale.US);
        return lr;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("siteLanguage");
        registry.addInterceptor(interceptor);
        registry.addInterceptor(deptEmpInter);
    }
    
}