package com.grayash.crud.manageuser.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OauthRequest {
	
	private String grant_type;
	private String password;
	private String username;
	

}
