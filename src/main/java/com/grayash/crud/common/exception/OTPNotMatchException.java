package com.grayash.crud.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OTPNotMatchException extends PCRuntimeException{

    private static final Logger Log = LoggerFactory.getLogger(OTPNotMatchException.class);
    private int count=0;



    public OTPNotMatchException(String customerId){
        super(customerId);

        if(Log.isErrorEnabled())
            Log.error("Throwing OTPNotMatchException");
    }

    public void setOtpErrorCount(int count){
        this.count = count;
    }

    public int getOtpErrorCount(){
        return this.count;
    }
}
