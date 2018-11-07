package com.grayash.crud.common.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ValidateOTPRequest extends CommonRequest{

    private String otp;
    private String phoneNumber;
    private String phoneNumberCountryCode;
}
