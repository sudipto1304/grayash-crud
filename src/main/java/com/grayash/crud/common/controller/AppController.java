package com.grayash.crud.common.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grayash.crud.common.model.request.InvalidOTPAttemptRequest;
import com.grayash.crud.common.model.request.OTPRequest;
import com.grayash.crud.common.model.request.OTPStatusChangeRequest;
import com.grayash.crud.common.model.response.ErrorMessageResponse;
import com.grayash.crud.common.model.response.OTPResponse;
import com.grayash.crud.common.model.response.Status;
import com.grayash.crud.common.model.response.ValidateOTPResponse;
import com.grayash.crud.common.service.AppCommonService;
import com.grayash.crud.common.util.CodeConstant;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app")
public class AppController implements CodeConstant {

    private static final Logger Log = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppCommonService service;



    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted", response = OTPResponse.class)
    })
    @RequestMapping(value="/otp/generate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OTPResponse> generateOtp(@RequestBody OTPRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Request to generate OTP for the customerId::"+request);
        OTPResponse response = service.generateOTP(request);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ValidateOTPResponse.class)
    })
    @RequestMapping(value="/otp/validate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidateOTPResponse> validateOtp(@RequestBody OTPRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Validate OTP Request::"+request);
        ValidateOTPResponse response = service.validateOTP(request);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class)
    })
    @RequestMapping(value="/message/{msgId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageResponse> getErrorMessage(@PathVariable("msgId") String msgId, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Requested message id is "+msgId);
        ErrorMessageResponse response = service.getErrorMessage(msgId);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ErrorMessageResponse.class)
    })
    @RequestMapping(value="/message/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ErrorMessageResponse>> getAllErrorMessage(HttpServletRequest servletRequest) {
    	List<ErrorMessageResponse> response = service.getAllErrorMessage();
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Status.class)
    })
    @RequestMapping(value="/cache/refresh", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> refreshCache(HttpServletRequest servletRequest) {
    	service.refreshCache();
    	Status response = new Status(HTTP_OK_STATUS, "", HttpStatus.OK);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Status.class)
    })
    @RequestMapping(value="/OTP/invalid", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> invalidOTPAttempts(@RequestBody InvalidOTPAttemptRequest request, HttpServletRequest servletRequest) {
    	Status response = service.increaseOTPCount(request);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Status.class)
    })
    @RequestMapping(value="/OTP/change/status", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOTPStatus(@RequestBody OTPStatusChangeRequest request, HttpServletRequest servletRequest) {
    	service.updateOTPStatus(request);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
    
}
