package com.example.usermanagement.dto.constant;

import com.example.usermanagement.enums.Role;
import lombok.Data;

@Data
public class UserDTO extends BaseEntityDTO {
    private String name;
    private String surname;
    private String username;
    private String telephone;
    private String email;
    private String password;
    private String managerId;
    private Role role;
}
