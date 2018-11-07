package com.grayash.crud.common.model.response;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class DeviceRegistrationResponse extends Status{
	private String appId;

}
