package com.example.usermanagement.dto.response;

import com.example.usermanagement.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO{

    private String id;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private String token;
}
