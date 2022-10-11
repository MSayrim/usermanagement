package com.example.usermanagement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    ADMIN("Admin"),
    USER("Kullanıcı");

    private final String screenLabel;
}

