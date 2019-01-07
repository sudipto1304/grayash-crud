package com.grayash.crud.manageuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class OauthFeignClientConfiguration {
	
	@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
         return new BasicAuthRequestInterceptor("a2c3db84-e3e4-4a6d-a66f-846bf414ee4b", "0e1affcd-e683-4dea-96c4-82e1f5234a3b");
    }

}
