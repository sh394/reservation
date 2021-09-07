package com.duke.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.duke.booking.interceptor.LogInterceptor;

public class WebMvcContextConfiguration implements WebMvcConfigurer {
	  
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
	    registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
	    registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	 }
	  
	  
	  @Override
	  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	  }
	  
	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	   
	    registry.addViewController("/").setViewName("main");
	  }

	  @Bean
	  public InternalResourceViewResolver getInternalResourceViewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/views/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	  }

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LogInterceptor());
	  }
	  
	  @Bean
	  public MultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(1024 * 1024 * 100); // 100MB
	    return multipartResolver;
	  }
}
