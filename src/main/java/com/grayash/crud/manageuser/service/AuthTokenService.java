package com.grayash.crud.manageuser.service;



import com.grayash.crud.common.controller.AppController;
import com.grayash.crud.manageuser.model.OauthToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthTokenService {
	
	private static final Logger Log = LoggerFactory.getLogger(AuthTokenService.class);


    @Autowired
    private LoadBalancerClient loadBalancer;

    @Value("${oauth2.application.name}")
    private String oauthAppName;

    public OauthToken getOauthToken(String userName, String password){
        ServiceInstance serviceInstance=loadBalancer.choose(oauthAppName);
        String baseUrl=serviceInstance.getUri().toString();
        baseUrl=baseUrl+"/security/oauth/token";
        if(Log.isInfoEnabled())
        	Log.info("Auth service URL ::"+baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OauthToken> response=null;
        Map<String, String> param = new HashMap<>();
        param.put("grant_type", "password");
        param.put("client_id", "1950c3e0-dfd5-44fc-a058-792506a95a45");
        param.put("client_secret", "ad9cd0b9-db4d-49b6-a722-b3a67544b2b7");
        param.put("username", userName);
        param.put("password", password);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(param, header);
        try{
            response=restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, OauthToken.class);
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
        return response.getBody();
    }
}
