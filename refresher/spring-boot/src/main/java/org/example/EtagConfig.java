package org.example;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class EtagConfig {
    //@Bean
//    public FilterRegistrationBean<ShallowEtagHeaderFilter> eTagFilter(){
//        FilterRegistrationBean eTagBean = new FilterRegistrationBean<ShallowEtagHeaderFilter>(new ShallowEtagHeaderFilter());
//        eTagBean.addUrlPatterns("/etag/*");
//        eTagBean.setName("eTagBean");
//        return eTagBean;
//    }

    @Bean
    public ShallowEtagHeaderFilter eTagFilter(){
        return new ShallowEtagHeaderFilter();
    }


}
