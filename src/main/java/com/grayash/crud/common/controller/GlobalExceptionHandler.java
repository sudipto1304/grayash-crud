package com.grayash.crud.common.controller;


import static com.grayash.crud.common.util.ErrorMsg.getErrorMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.grayash.crud.common.exception.CustomerIdNotFoundException;
import com.grayash.crud.common.exception.OTPExpiredException;
import com.grayash.crud.common.exception.OTPNotMatchException;
import com.grayash.crud.common.exception.OtpNotGeneratedException;
import com.grayash.crud.common.model.response.Status;
import com.grayash.crud.common.util.CodeConstant;
import com.grayash.crud.common.util.CommonUtils;
import com.grayash.crud.manageuser.exception.UserPresentException;

@SuppressWarnings("Duplicates")
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements CodeConstant {

    private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    @ExceptionHandler(UserPresentException.class)
    protected ResponseEntity<Object> handleGlobalException(UserPresentException ex, WebRequest request) {
        Status status  = new Status();
        status.setResponseCode(MSG_00001);
        status.setResponseMsg(getErrorMsg(MSG_00001));
        status.setHttpCode(HttpStatus.PRECONDITION_FAILED);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler(CustomerIdNotFoundException.class)
    protected ResponseEntity<Object> handleGlobalException(CustomerIdNotFoundException ex, WebRequest request) {
        Status status  = new Status();
        status.setResponseCode(MSG_00003);
        status.setResponseMsg(getErrorMsg(MSG_00003));
        status.setHttpCode(HttpStatus.PRECONDITION_FAILED);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.PRECONDITION_FAILED, request);
    }


    @ExceptionHandler(OtpNotGeneratedException.class)
    protected ResponseEntity<Object> handleGlobalException(OtpNotGeneratedException ex, WebRequest request) {
        Status status  = new Status();
        status.setResponseCode(MSG_00004);
        status.setResponseMsg(getErrorMsg(MSG_00004));
        status.setHttpCode(HttpStatus.PRECONDITION_FAILED);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.PRECONDITION_FAILED, request);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleGlobalException(DataIntegrityViolationException ex, WebRequest request) {
        Status status  = new Status();
        status.setResponseCode(MSG_00010);
        status.setResponseMsg(getErrorMsg(MSG_00010));
        status.setHttpCode(HttpStatus.PRECONDITION_FAILED);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.PRECONDITION_FAILED, request);
    }


    @ExceptionHandler(OTPExpiredException.class)
    protected ResponseEntity<Object> handleGlobalException(OTPExpiredException ex, WebRequest request) {
        Status status  = new Status();
        status.setResponseMsg(MSG_00005);
        status.setResponseMsg(getErrorMsg(MSG_00005));
        status.setHttpCode(HttpStatus.UPGRADE_REQUIRED);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.PRECONDITION_FAILED, request);
    }

    @ExceptionHandler(OTPNotMatchException.class)
    protected ResponseEntity<Object> handleGlobalException(OTPNotMatchException ex, WebRequest request) {
        int count = ex.getOtpErrorCount();
        Status status  = new Status();
        int leftCount = 3-count;
        if(leftCount>1){
            status.setResponseMsg(MSG_00006);
            status.setResponseMsg(getErrorMsg(MSG_00006).replace("$#$", String.valueOf(count)));
            status.setHttpCode(HttpStatus.NOT_ACCEPTABLE);
        }else if(leftCount==1){
            status.setResponseMsg(MSG_00007);
            status.setResponseMsg(getErrorMsg(MSG_00007).replace("$#$", String.valueOf(count)));
            status.setHttpCode(HttpStatus.NOT_ACCEPTABLE);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        if(Log.isErrorEnabled())
            Log.error("Exception::", ex);
        Status status  = new Status();
        status.setResponseMsg(MSG_99999);
        status.setResponseMsg(getErrorMsg(MSG_99999));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
