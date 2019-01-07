package com.grayash.crud.manageuser.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.grayash.crud.manageuser.config.OauthFeignClientConfiguration;
import com.grayash.crud.manageuser.model.response.OauthToken;

import feign.Headers;

@FeignClient(name = "OAuth2Security", configuration=OauthFeignClientConfiguration.class)
public interface OauthClient {
	
	@RequestMapping(method = RequestMethod.POST, path = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
    OauthToken getTokens(@RequestParam(value="grant_type") String grantType, @RequestParam(value="username") String userName, @RequestParam(value="password") String password);

}
