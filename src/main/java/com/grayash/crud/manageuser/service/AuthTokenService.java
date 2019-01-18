package com.grayash.crud.manageuser.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.grayash.crud.manageuser.clients.OauthClient;
import com.grayash.crud.manageuser.model.response.OauthToken;

@Component
public class AuthTokenService {
	
	private static final Logger Log = LoggerFactory.getLogger(AuthTokenService.class);


    @Autowired
    private OauthClient oauthCLient;

    @Value("${oauth2.application.name}")
    private String oauthAppName;

    public OauthToken getOauthToken(String userName, String password){
        OauthToken response = oauthCLient.getTokens("password", userName, password);
        Log.debug("Oauth Response:::"+response);
        return response;
    }
}
