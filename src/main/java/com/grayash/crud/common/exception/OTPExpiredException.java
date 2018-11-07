package com.grayash.crud.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OTPExpiredException extends PCRuntimeException{

    private static final Logger Log = LoggerFactory.getLogger(OTPExpiredException.class);



    public OTPExpiredException(String customerId){
        super(customerId);
        if(Log.isErrorEnabled())
            Log.error("Throwing OTPExpiredException");
    }


}
