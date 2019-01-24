package com.grayash.crud.common.model.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.grayash.crud.common.util.CodeConstant;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Status implements Serializable {

    private String responseCode;
    private String responseMsg;
    private HttpStatus httpCode;


    public Status(){
    	this.responseCode = HttpStatus.OK.name();
    	this.responseMsg = CodeConstant.SUCCESS;
    	this.httpCode = httpCode.OK;
    }


    public Status(String responseCode, String responseMsg, HttpStatus httpCode){
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.httpCode = httpCode;
    }

}
