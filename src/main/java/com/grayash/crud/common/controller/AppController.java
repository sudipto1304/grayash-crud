package com.grayash.crud.common.controller;


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

import com.grayash.crud.common.model.request.OTPRequest;
import com.grayash.crud.common.model.request.ValidateOTPRequest;
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
            @ApiResponse(code = 202, message = "Accepted", response = OTPResponse.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/otp/generate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OTPResponse> generateOtp(@RequestBody OTPRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Request to generate OTP for the customerId::"+request);
        OTPResponse response = service.generateOTP(request);
        response.setStatus(new Status(HTTP_OK_STATUS, "", HttpStatus.ACCEPTED));
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ValidateOTPResponse.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 426, message = "Upgrade Required"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/otp/validate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidateOTPResponse> validateOtp(@RequestBody ValidateOTPRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Validate OTP Request::"+request);
        ValidateOTPResponse response = service.validateOTP(request);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 426, message = "Upgrade Required"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/message/{msgId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getErrorMessage(@PathVariable("msgId") String msgId, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Requested message id is "+msgId);
        String response = service.getErrorMessage(msgId);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
