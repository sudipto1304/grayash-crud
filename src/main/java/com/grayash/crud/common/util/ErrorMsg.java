package com.grayash.crud.common.util;

import java.util.HashMap;
import java.util.Map;

import com.github.grayash.exception.InvalidMessageCodeException;

public class ErrorMsg {

    private static Map<String, String> errorMsg=new HashMap<>();

    public static String getErrorMsg(String msgCode){
        if(errorMsg!=null){
            return errorMsg.get(msgCode);
        }else {
        	throw new InvalidMessageCodeException();
        }
        
    }


    protected void putErrorMsg(String key, String value){
        this.errorMsg.put(key, value);
    }
}
