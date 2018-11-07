package com.grayash.crud.common.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grayash.crud.common.model.request.CommonRequest;
import com.grayash.crud.common.model.request.DeviceRegisterRequest;
import com.grayash.crud.common.model.request.ValidateOTPRequest;
import com.grayash.crud.common.model.response.DeviceRegistrationResponse;
import com.grayash.crud.common.model.response.OTPResponse;
import com.grayash.crud.common.model.response.Status;
import com.grayash.crud.common.service.AppCommonService;
import com.grayash.crud.common.util.CodeConstant;
import com.grayash.crud.common.util.CommonUtils;
import com.grayash.crud.manageuser.model.ManagerUserResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app")
public class AppController implements CodeConstant {

    private static final Logger Log = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppCommonService service;



    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted", response = OTPResponse.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/otp/generate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateOtp(@RequestBody CommonRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Request to generate OTP for the customerId::"+request);
        OTPResponse response = service.generateOTP(request);
        response.setStatus(new Status(HTTP_OK_STATUS, "", HttpStatus.ACCEPTED));
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(CommonUtils.constructJsonResponse(response), HttpStatus.ACCEPTED);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 426, message = "Upgrade Required"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/otp/validate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateOtp(@RequestBody ValidateOTPRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Validate OTP Request::"+request);
        Status status = service.validateOTP(request.getCustomerId(), request.getOtp());
        if(Log.isDebugEnabled())
            Log.debug("returning response "+status);
        return new ResponseEntity<>(CommonUtils.constructJsonResponse(status), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 426, message = "Upgrade Required"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/statuscheck", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatus(HttpServletRequest servletRequest) {
        Status status = new Status("200_OK", "SERVER_OK", HttpStatus.OK);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+status);
        return new ResponseEntity<>(CommonUtils.constructJsonResponse(status), HttpStatus.OK);
    }
    
    
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Created", response = DeviceRegistrationResponse.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/device/register", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceRegistrationResponse> registerDevice(@RequestBody DeviceRegisterRequest request) {
    	DeviceRegistrationResponse response = service.registerDevice(request);
    	if(Log.isDebugEnabled())
            Log.debug("device app id generated "+response.getAppId());
    	return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}