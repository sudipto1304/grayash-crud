package com.grayash.crud.common.util;

import java.util.HashMap;
import java.util.Map;

import com.grayash.exception.InvalidMessageCodeException;

public class ErrorMsg {

    private static Map<String, String> errorMsg=new HashMap<>();

    public static String getErrorMsg(String msgCode){
        if(errorMsg!=null && errorMsg.get(msgCode)!=null){
            return errorMsg.get(msgCode);
        }else {
        	throw new InvalidMessageCodeException();
        }
        
    }
    
    
    public static Map<String, String> getAllErrorMsg(){
        return errorMsg;
        
    }


    public static void putErrorMsg(String key, String value){
        errorMsg.put(key, value);
    }
}
