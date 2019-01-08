package com.grayash.crud.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.github.grayash.security.filter.JWTAuthorizationFilter;
import com.github.grayash.security.filter.OauthEntryPoint;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Bean 
	OauthEntryPoint enrtyPoint() {
		return new OauthEntryPoint();
	}
	

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/securityNone").permitAll()
          .anyRequest().authenticated()
          .and()
          .httpBasic()
          .authenticationEntryPoint(enrtyPoint());;
 
        http.addFilterAfter(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
        http.cors().disable();
    }
}
