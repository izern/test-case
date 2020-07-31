package cn.izern.test.case1;

import cn.izern.test.case1.advice.DecryptRequestBodyAdvice;
import cn.izern.test.case1.servlet.filter.CustomHttpServletRequestFilter;
import cn.izern.test.case1.servlet.filter.CustomHttpServletRequestFilter2;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zern
 * @since 1.0.0
 */
@SpringBootApplication
@ServletComponentScan
public class Case1Application {

  // 如下配置，开启一个即可。

  /**
   * 1. 问题复现，出现错误
   * @return filterRegistrationBean
   */
//  @Bean
//  public FilterRegistrationBean filterRegist() {
//    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new
//        CustomHttpServletRequestFilter());
//    filterRegistrationBean.setOrder(-99);
//    filterRegistrationBean.setName("paramDecryptFilter");
//    filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//    return filterRegistrationBean;
//  }

  /**
   * 2. 解决问题1，修复request bug
   * @return filterRegistrationBean
   */
//  @Bean
//  public FilterRegistrationBean filterRegist() {
//    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new
//        CustomHttpServletRequestFilter2());
//    filterRegistrationBean.setOrder(-99);
//    filterRegistrationBean.setName("paramDecryptFilter");
//    filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//    return filterRegistrationBean;
//  }

  /**
   * 3. DecryptRequestBodyAdvice.java 实现需求 给这个类添加ControllerAdvice
   */


  public static void main(String[] args) {
    SpringApplication.run(Case1Application.class, args);
  }
}
