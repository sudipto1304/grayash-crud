package com.grayash.crud.common.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ERROR_MESSAGE_VIEW")
@Data
public class ErrorMsgEntity implements Serializable {

    @Id
    @Column(name="MSG_CODE")
    private String msgCode;

    @Column(name="MSG_TEXT")
    private String msgText;

    @Column(name="LOCALE")
    private String locale;

    @Column(name="MSG_TYPE")
    private String msgType;

}
