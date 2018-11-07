package com.grayash.crud.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CommonUtils {

    private static final Logger Log = LoggerFactory.getLogger(CommonUtils.class);

    public static int getOTP() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(99999);
    }

    public static String constructJsonResponse(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(object);
        }catch (Exception e){
            if(Log.isErrorEnabled())
                Log.error("Exception::", e);
        }
        return null;

    }
}
