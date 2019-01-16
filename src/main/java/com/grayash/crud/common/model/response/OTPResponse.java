package com.grayash.crud.common.model.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OTPResponse {

    private String phoneNumber;
    private String phoneNumberCountryCode;
    private OTPStatus otpStatus;
    private Status status;

}
