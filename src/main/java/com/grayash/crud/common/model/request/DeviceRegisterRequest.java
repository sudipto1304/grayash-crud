package com.grayash.crud.common.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeviceRegisterRequest extends CommonRequest implements Serializable{
	private String deviceType;
	private String countryCode;

}
