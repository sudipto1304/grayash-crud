package com.grayash.crud.manageuser.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grayash.crud.manageuser.config.OauthFeignClientConfiguration;
import com.grayash.crud.manageuser.model.request.OauthRequest;
import com.grayash.crud.manageuser.model.response.OauthToken;

@FeignClient(name = "OAuth2Security", configuration=OauthFeignClientConfiguration.class)
public interface OauthClient {
	
	@RequestMapping(method = RequestMethod.POST, path = "/oauth/token")
    OauthToken getTokens(OauthRequest request);

}
