package com.grayash.crud.common.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DATA_CUSTOMER_OTP")
@Data
public class OTPEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name="OTP_GENERATION_TIME")
    private String generationTime;
    @Column(name="CUSTOMER_ID")
    private String customerId;
    @Column(name="PHONE_NUMBER_COUNTRY_CODE")
    private String phoneNumberCountry;
    @Column(name="count")
    private int count;


}
