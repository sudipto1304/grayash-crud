package com.grayash.crud.manageuser.model;

import com.grayash.crud.common.model.response.Status;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ManagerUserResponse{

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
    private Status status;
    private OauthToken token;
}
