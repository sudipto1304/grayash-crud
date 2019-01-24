package com.grayash.crud.manageuser.model.response;

import com.grayash.crud.common.model.response.Status;
import com.grayash.crud.manageuser.model.request.AccountStatus;
import com.grayash.crud.manageuser.model.request.Verify;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ManagerUserResponse extends Status{

    private String firstName;
    private String lastName;
    private String address;
    private String userId;
    private String phoneNumber;
    private String emailId;
    private String phoneNumberCountryCode;
    private AccountStatus accountStatus;
    private Verify verifyStatus;
    private String customerId;
    private OauthToken token;
}
