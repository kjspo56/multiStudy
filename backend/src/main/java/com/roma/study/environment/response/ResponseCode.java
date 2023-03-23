package com.roma.study.environment.response;

public enum ResponseCode {
    FAIL("0"),
    SUCCESS("1"),
    NOT_POST("2"),
    DB_ERROR("3"),
    LOGIN_CONFIRM_FAIL("10"),
    LOGIN_CONFIRM_STAY("11"),
    LOGIN_CONFIRM_MAIL("12"),
    LOGIN_CONFIRM_PAUSE("13"),
    LOGIN_CONFIRM_STOP("14"),
    LOGIN_NOT_FOUND("15"),
    JWT_TOKEN_EXPIRED("16"),
    INVALID_PASSWORD("17"),
    API_NOT_FOUND("100"),
    NO_DATA_FOUND("101"),
    INVALID_FORMAT("102"),
    FILE_NOT_FOUND("103"),
    UNAUTHORIZED("401"),
    NO_AUTHORIZED("403"),
    PAGE_NOT_FOUND("404"),
    CONFLICT_DATA("409"),
    UNSUPPORTED_MEDIA_TYPE("415"),
    QUEST_ADMIM("801"),
    GO_TO_MAIN_TEXT("802"),
    FAIL_SEND_MAIL("803"),
    PWD_INIT_TEXT("810"),
    PWD_INIT_COMPLETE_TEXT("811"),
    PWD_INIT_TAG("812"),
    PWD_INIT_ADMIN_TEXT("813"),
    PWD_REGIST_TEXT("814"),
    JOIN_USER_TEXT("820"),
    ADD_USER_TEXT("821"),
    LOGIN_TEXT("822"),
    CLICK_STEP_TEXT("823"),
    REGIST_TEXT("824"),
    CUBE_PWD_CHANGE_TEXT("830"),
    PWD_CHANGE_TEXT("831"),
    PWD_CHANGE_TAG("832"),
    PWD_CHANGE_CLICK_TEXT("833"),
    PWD_CHANGE_REGIST_TEXT("834"),
    CUBE_AUTH_TEXT("840"),
    AUTH_CONFIRM_TEXT("841"),
    AUTH_CODE_TAG("842"),
    AUTH_REQ_TAG("843"),
    AUTH_NOTICE_TEXT("844"),
    INVALID_PARAMETER("901"),
    INVALID_PARAMETER_FORMAT("902"),
    BROKEN_FILE("903"),
    OBJECT_MAPPING_FAIL("904"),
    PRESET_CONVERT_ERROR("90%"),
    OTHERS("999");

    private final String code;

    private ResponseCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
