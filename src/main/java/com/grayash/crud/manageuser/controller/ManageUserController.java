package com.grayash.crud.manageuser.controller;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grayash.crud.common.model.response.Status;
import com.grayash.crud.common.util.CodeConstant;
import com.grayash.crud.manageuser.model.request.ManageUserRequest;
import com.grayash.crud.manageuser.model.request.OauthRequest;
import com.grayash.crud.manageuser.model.response.ManagerUserResponse;
import com.grayash.crud.manageuser.model.response.OauthToken;
import com.grayash.crud.manageuser.service.OauthTokenService;
import com.grayash.crud.manageuser.service.UserRegistrationService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/manageuser")
public class ManageUserController implements CodeConstant {
	
	private static final Logger Log = LoggerFactory.getLogger(ManageUserController.class);
	
	@Autowired
	private UserRegistrationService service;
	
	
	@Autowired
	private OauthTokenService tokenService;




    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ManagerUserResponse.class)
    })
	@RequestMapping(value="/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ManagerUserResponse> registerUser(@RequestBody ManageUserRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) {
		if(Log.isDebugEnabled())
			Log.debug("Registration Request::"+request);
		ManagerUserResponse response = service.registerUser(request);
		response.setHttpCode(HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
    
    
    @RequestMapping(value="/OauthTest", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OauthToken> getOauthToken(@RequestBody OauthRequest request, HttpServletRequest servletRequest, @RequestHeader HttpHeaders headers) {
		if(Log.isDebugEnabled())
			Log.debug("OauthTest Request::"+request);
		OauthToken response = tokenService.getOauthToken(request.getUsername(), request.getPassword());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
