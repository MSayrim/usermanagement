package com.example.usermanagement.dto.constant;

import lombok.Data;

@Data
public class ShortUserDTO extends BaseEntityDTO {
    private String id;
    private String name;
    private String surname;
}
