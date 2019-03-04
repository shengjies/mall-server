package com.code.mallservice.mall.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.util.Locale;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        LoginUserInterceptor interceptor = new LoginUserInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(interceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/static/**");
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/sendA");
        loginRegistry.excludePathPatterns("/sendB");
        loginRegistry.excludePathPatterns("/activation/save");
        loginRegistry.excludePathPatterns("/ads");
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolverBean(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        //设置默认区域,
        localeResolver.setDefaultLocale(Locale.US);
        localeResolver.setCookieMaxAge(3600);//设置cookie有效期.
        return localeResolver;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(20));
        factory.setMaxRequestSize(DataSize.ofMegabytes(20));
        return factory.createMultipartConfig();
    }
}
