package com.grayash.crud.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "EVENT_ACTIVITY_LOG")
@Data
public class AuditActivityEntity implements Serializable {

    @Column(name="CUSTOMER_ID")
    private String customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AUDIT_ID")
    private Long auditId;

    @Column(name="REQUEST_URI")
    private String requestUri;

    @Column(name="ACTIVITY_TYPE")
    private String activityType;

    @Column(name="ACTIVITY_DATA")
    private String activityData;

    @Column(name="TIMESTAMP")
    private String timestamp;

    @Column(name="RESPONSE_CODE")
    private String responseCode;

    @Column(name="EXCEPTION")
    private String exception;

    @Column(name="DEVICE_ID")
    private String deviceId;

    @Column(name="OS")
    private String os;

    @Column(name="OS_VERSION")
    private String osVersion;

    @Column(name="ISP")
    private String isp;

    @Column(name="IP_ADDRESS")
    private String ipAddress;





}
