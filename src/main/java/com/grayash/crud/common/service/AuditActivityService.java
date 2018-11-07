package com.grayash.crud.common.service;


import com.grayash.crud.common.entity.AuditActivityEntity;
import com.grayash.crud.common.model.request.CommonRequest;
import com.grayash.crud.common.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AuditActivityService {

    private static final Logger Log = LoggerFactory.getLogger(AuditActivityService.class);

    @Autowired
    private AsyncService service;

    public void writeRequest(String URI, Object request, String customerId, HttpHeaders headers) throws InterruptedException {
        service.insertAuditActivityData(URI, CommonUtils.constructJsonResponse(request),customerId, HttpStatus.OK, headers, "REQUEST");
    }

    public void writeResponse(String URI, Object response, String customerId, HttpStatus status) throws InterruptedException {
        service.insertAuditActivityData(URI, CommonUtils.constructJsonResponse(response),customerId, status, "RESPONSE");

    }


    public void writeException(String URI, Object response, String customerId, HttpStatus status, RuntimeException ex) throws InterruptedException {
        service.insertAuditActivityExceptionData(URI, CommonUtils.constructJsonResponse(response),customerId, status, "EXCEPTION", ex);

    }




}
