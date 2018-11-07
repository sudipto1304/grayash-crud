package com.grayash.crud.common.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeviceRegisterRequest implements Serializable{
	private String deviceType;
	private String os;
	private String osVersion;
	private String countryCode;
	private String isp;
	private String ip;

}
