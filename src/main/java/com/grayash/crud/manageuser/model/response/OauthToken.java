package com.grayash.crud.manageuser.model.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class OauthToken implements Serializable {

    private String access_token;
    private String token_type;
    private String scope;
    private String jti;



}
