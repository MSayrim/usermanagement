package com.example.usermanagement.dto.request;

import com.example.usermanagement.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class GenericServiceResult implements Serializable {

    private ResponseStatus responseStatus;
    private int responseCode;
    private String responseDescription;
    private Object responseData;
    private String serviceCode = "001";  // depends on micro-service must be unique

    public GenericServiceResult(ResponseStatus responseStatus, int responseCode, String responseDescription, Object responseData) {
        this.responseStatus = responseStatus;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.responseData = responseData;
    }
}