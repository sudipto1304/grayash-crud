package com.grayash.crud.manageuser.model.request;

import java.io.Serializable;

import com.grayash.crud.common.model.request.CommonRequest;
import com.grayash.crud.common.model.response.ClientDetails;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ManageUserRequest extends CommonRequest implements Serializable{
	
	private String userId;
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String address;
    private String contactNumber;
	private int countryCode;
	private boolean isTncAccepted;

    


}
