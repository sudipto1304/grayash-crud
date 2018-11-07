package com.grayash.crud.common.model.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CommonRequest implements Serializable {

    private String customerId;
    private String flowId;
}
