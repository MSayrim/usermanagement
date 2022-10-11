package com.example.usermanagement.enums;

public enum ErrorType {

    INTERNAL_ERROR("internal_error"),
    RUNTIME_EXCEPTION("runtime_exception"),
    ACCESS_DENIED("access_denied"),
    METHOD_ARGUMENT_NOT_VALID("method_argument_not_valid"),

    ENTITY_NOT_FOUND("entity_not_found"),
    BAD_CREDENTIAL("bad_credentials"),
    USERNAME_ALREADY_IN_USE("username_already_in_use"),
    TELEPHONE_ALREADY_IN_USE("telephone_already_in_use"),
    IDENTITY_NUMBER_EXIST("identity_number_exist"),
    INVALID_DATE_EXCEPTION("invalid_date_exception"),
    START_DATE_MUST_BE_AFTER_END_DATE("start_date_must_be_after_end_date"),
    INVALID_DATE_AND_HOUR_EXCEPTION("invalid_date_and_hour_exception"),
    ALREADY_IN_BLACK_LIST_EXCEPTION("already_in_black_list_exception");

    private final String error;

    ErrorType(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
