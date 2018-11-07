package com.grayash.crud.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EVENT_INSTALLED_APP_DEVICE_INFORMATION")
@Data
public class InstalledAppDeviceEntity implements Serializable{
	
	
	@Id
    @Column(name="APP_ID")
	private String appId;
	@Column(name="DEVICE_TYPE")
	private String deviceType;
	@Column(name="OS")
	private String os;
	@Column(name="OS_VERSION")
	private String osVersion;
	@Column(name="CURRENT_COUNTRY_CODE")
	private String countryCode;
	@Column(name="ISP")
	private String isp;
	@Column(name="IP_ADDRESS")
	private String ip;
	@Column(name="TIMESTAMP")
	private String time;

}
