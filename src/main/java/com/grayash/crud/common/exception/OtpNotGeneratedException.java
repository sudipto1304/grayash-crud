package com.grayash.crud.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtpNotGeneratedException extends PCRuntimeException{

    private static final Logger Log = LoggerFactory.getLogger(OtpNotGeneratedException.class);


    public OtpNotGeneratedException(String customerId){
        super(customerId);
        if(Log.isErrorEnabled())
            Log.error("Throwing OtpNotGeneratedException");
    }
}
