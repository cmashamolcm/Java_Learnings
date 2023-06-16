package com.auto.config.customautoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

//@Configuration
@AutoConfiguration
public class CustomAutoConfig {

    @Bean
    public CustomBean getCustomBean(){
        return new CustomBean();
    }

}
