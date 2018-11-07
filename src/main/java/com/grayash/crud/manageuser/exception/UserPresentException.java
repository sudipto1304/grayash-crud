package com.grayash.crud.manageuser.exception;

import com.grayash.crud.common.exception.PCRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPresentException extends PCRuntimeException {

    private static final Logger Log = LoggerFactory.getLogger(UserPresentException.class);



    public UserPresentException(String customerId){
        super(customerId);
        if(Log.isErrorEnabled())
            Log.error("Throwing UserPresentException");

    }
}
