package com.grayash.crud.common.model.request;

import java.io.Serializable;

import com.grayash.crud.common.model.response.OTPStatus;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class OTPStatusChangeRequest implements Serializable{

	private String phoneNumber;
	private FlowType flowType;
	private OTPStatus status;
}
