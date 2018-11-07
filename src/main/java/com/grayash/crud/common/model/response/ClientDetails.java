package com.grayash.crud.common.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientDetails implements Serializable{
	
	private String deviceId;
	private String operatingSystem;
	private String operatingSystemVersion;
	private String requestTime;
	private String isp;
	private String ipAddress;
	
	@Override
	public String toString() {
		return "CommonRequestData [deviceId=" + deviceId + ", operatingSystem=" + operatingSystem
				+ ", operatingSystemVersion=" + operatingSystemVersion + ", requestTime=" + requestTime + ", isp=" + isp
				+ ", ipAddress=" + ipAddress + "]";
	}
	
	
	
}
