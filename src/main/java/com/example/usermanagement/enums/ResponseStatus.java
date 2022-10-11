package com.example.usermanagement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseStatus {

    SUCCESS(200, "SUCCESS"),
    HANDLED_EXCEPTION(498, "HANDLED_EXCEPTION"),
    ERROR(499, "ERROR");

    private final int resultCode;
    private final String resultMessage;

}
