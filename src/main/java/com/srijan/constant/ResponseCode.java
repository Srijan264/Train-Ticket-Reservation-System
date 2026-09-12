package com.srijan.constant;

public enum ResponseCode {
    SUCCESS(200, "Operation Completed Successfully"),
    FAILURE(400, "Operation Failed"),
    UNAUTHORIZED(401, "Unauthorized Access"),
    NOT_FOUND(404, "Resource Not Found"),
    INTERNAL_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
