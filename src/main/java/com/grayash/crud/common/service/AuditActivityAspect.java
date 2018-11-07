package com.grayash.crud.common.service;


import com.grayash.crud.common.exception.PCRuntimeException;
import com.grayash.crud.common.model.request.CommonRequest;
import com.grayash.crud.common.model.response.ClientDetails;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@SuppressWarnings("Duplicates")
@Aspect
@Configuration
@Component
public class AuditActivityAspect {


    private static final Logger Log = LoggerFactory.getLogger(AuditActivityAspect.class);

    @Autowired
    private AuditActivityService service;

    @AfterReturning(pointcut="execution(* com.grayash.crud..controller..handleGlobalException(..))", returning = "result")
    public void auditException(JoinPoint joinPoint, Object result) throws Throwable {
        try{
            Object[] arr = joinPoint.getArgs();
            PCRuntimeException exception = null;
            ServletWebRequest str = null;
            for(Object obj : arr){
                if(obj instanceof PCRuntimeException)
                    exception = (PCRuntimeException)obj;
                else if(obj instanceof ServletWebRequest)
                    str = (ServletWebRequest)obj;
            }
            ResponseEntity entity = (ResponseEntity)result;
            service.writeException(str.getRequest().getRequestURI(), entity.getBody(), exception.getCustomerId(), entity.getStatusCode(), exception);
        }catch(Exception e){
            e.printStackTrace();
        }



    }

    @AfterReturning(pointcut="execution(* com.grayash.crud..controller..*(..)) && !execution(* com.grayash.crud..controller..handleGlobalException(..))", returning = "result")
    public void auditResponse(JoinPoint joinPoint, Object result) throws Throwable {
        try {
            Object[] arr = joinPoint.getArgs();
            HttpServletRequest servletRequest = null;
            CommonRequest request =null;
            for(Object obj : arr){
                if(obj instanceof CommonRequest)
                    request = (CommonRequest)obj;
                else if(obj instanceof HttpServletRequest)
                    servletRequest = (HttpServletRequest)obj;
            }
            ResponseEntity entity = (ResponseEntity) result;
            String customerId = (request!=null && request.getCustomerId()!=null)?request.getCustomerId():"";
            service.writeResponse(servletRequest.getRequestURI(), entity.getBody(), customerId, entity.getStatusCode());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Before("execution(* com.grayash.crud..controller..*(..)) && !execution(* com.grayash.crud..controller..handleGlobalException(..))")
    public void auditRequest(JoinPoint joinPoint) throws Throwable {
        try{
            Object[] arr = joinPoint.getArgs();
            CommonRequest request =null;
            HttpServletRequest servletRequest = null;
            HttpHeaders headers = null;
            for(Object obj : arr){
                if(obj instanceof CommonRequest)
                    request = (CommonRequest)obj;
                else if(obj instanceof HttpServletRequest)
                    servletRequest = (HttpServletRequest)obj;
                else if(obj instanceof HttpHeaders)
                    headers = (HttpHeaders)obj;
            }
            if(request!=null && servletRequest!=null){
                service.writeRequest(servletRequest.getRequestURI(), request, StringUtils.isNotEmpty(request.getCustomerId())?request.getCustomerId():"", headers);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }



    }

}
