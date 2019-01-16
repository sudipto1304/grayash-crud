package com.grayash.crud.common.entity;


import lombok.Data;

import javax.persistence.*;

import com.grayash.crud.common.model.request.FlowType;
import com.grayash.crud.common.model.response.OTPStatus;

import java.io.Serializable;

@Entity
@Table(name = "DATA_CUSTOMER_OTP")
@Data
public class OTPEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="OTP")
    private String OTP;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;
    @Column(name="FLOW_ID")
    private String flowId;
    @Column(name="UPDATE_TIME")
    private String updateTime;
    @Column(name="STATUS")
    private String status;
    @Column(name="PHONE_NUMBER_COUNTRY_CODE")
    private String phoneNumberCountry;
    @Column(name="ATTEMPT_COUNT")
    private int count;


}
