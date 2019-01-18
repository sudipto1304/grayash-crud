package com.grayash.crud.manageuser.model.response;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OauthToken implements Serializable {

    private String access_token;
    private String token_type;
    private String scope;
    private String jti;



}
