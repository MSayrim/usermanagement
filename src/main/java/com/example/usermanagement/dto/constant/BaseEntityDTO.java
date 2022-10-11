package com.example.usermanagement.dto.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class BaseEntityDTO implements Serializable {
    private String id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd hh:MM")
    private ZonedDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd hh:MM")
    private ZonedDateTime modificationDate;

    private Boolean active = true;
}