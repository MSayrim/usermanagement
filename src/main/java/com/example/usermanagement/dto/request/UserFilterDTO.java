package com.example.usermanagement.dto.request;

import com.example.usermanagement.dto.constant.GenericPage;
import com.example.usermanagement.enums.Role;
import lombok.Data;

@Data
public class UserFilterDTO {
    private String nameSurname;
    private String telephone;
    private Role role;
    private GenericPage genericPage;
}
