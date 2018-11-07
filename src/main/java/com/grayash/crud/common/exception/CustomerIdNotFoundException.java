package com.grayash.crud.common.exception;

import com.grayash.crud.manageuser.exception.UserPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerIdNotFoundException extends PCRuntimeException{

    private static final Logger Log = LoggerFactory.getLogger(CustomerIdNotFoundException.class);



    public CustomerIdNotFoundException(String customerId){
        super(customerId);
        if(Log.isErrorEnabled())
            Log.error("Throwing CustomerIdNotFoundException");
    }



}
