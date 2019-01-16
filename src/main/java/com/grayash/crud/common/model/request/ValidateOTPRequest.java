package com.grayash.crud.common.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ValidateOTPRequest extends CommonRequest implements Serializable{

    private String otp;
    private String phoneNumber;
    private String phoneNumberCountryCode;
}
