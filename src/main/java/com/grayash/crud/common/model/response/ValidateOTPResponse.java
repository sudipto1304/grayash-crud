package com.grayash.crud.common.model.response;

import java.io.Serializable;

import com.grayash.crud.common.model.request.FlowType;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ValidateOTPResponse implements Serializable{
	
	private String otp;
	private String phoneNumber;
	private FlowType flowId;
	private String countryCode;
	private int attemptCount;

}
