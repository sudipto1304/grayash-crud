package com.grayash.crud.common.service;

import com.grayash.crud.common.entity.AuditActivityEntity;
import com.grayash.crud.common.repository.AuditActivityRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class AsyncService {


    private static final Logger Log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private AuditActivityRepository repository;


    @Async
    public void insertAuditActivityData(String URI, String data, String customerId, HttpStatus status, String type) throws InterruptedException {
        try {
            AuditActivityEntity entity = new AuditActivityEntity();
            entity.setActivityData(StringUtils.isNotEmpty(data) ? data : "");
            entity.setActivityType(StringUtils.isNotEmpty(type) ? type : "");
            entity.setCustomerId(customerId);
            entity.setRequestUri(URI);
            entity.setResponseCode(status.toString());
            repository.save(entity);
        }catch (Exception e){
            if(Log.isErrorEnabled())
                Log.error("", e);
        }
    }


    @Async
    public void insertAuditActivityData(String URI, String data, String customerId, HttpStatus status, HttpHeaders headers, String type) throws InterruptedException {
        try {
            AuditActivityEntity entity = new AuditActivityEntity();
            entity.setDeviceId(headers.get("deviceId").get(0));
            entity.setOs(headers.get("OS").get(0));
            entity.setOsVersion(headers.get("OS-VERSION").get(0));
            entity.setIpAddress(headers.get("IP").get(0));
            entity.setIsp(headers.get("ISP").get(0));
            entity.setActivityData(StringUtils.isNotEmpty(data) ? data : "");
            entity.setActivityType(StringUtils.isNotEmpty(type) ? type : "");
            entity.setCustomerId(customerId);
            entity.setRequestUri(URI);
            entity.setResponseCode(status.toString());
            repository.save(entity);
        }catch (Exception e){
            if(Log.isErrorEnabled())
                Log.error("", e);
        }
    }



    @Async
    public void insertAuditActivityExceptionData(String URI, String data, String customerId, HttpStatus status, String type, RuntimeException ex) throws InterruptedException {
        try {
            AuditActivityEntity entity = new AuditActivityEntity();
            entity.setActivityData(StringUtils.isNotEmpty(data) ? data : "");
            entity.setActivityType(StringUtils.isNotEmpty(type) ? type : "");
            entity.setCustomerId(customerId);
            entity.setRequestUri(URI);
            entity.setResponseCode(status.toString());
            entity.setException(ex.getClass().getName());
            repository.save(entity);
        }catch (Exception e){
            if(Log.isErrorEnabled())
                Log.error("", e);
        }
    }
}
