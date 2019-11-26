package com.sand.biz.exception;

public class PermissionException extends BaseException {
    private static final String DEFAULT_CODE = "permission.error";

    public PermissionException() {
        super(DEFAULT_CODE);
        setCode(DEFAULT_CODE);
    }

    public PermissionException(String message, Object... args) {
        super(message, args);
        setCode(DEFAULT_CODE);
    }

    public PermissionException(Throwable cause) {
        super(cause, DEFAULT_CODE);
        setCode(DEFAULT_CODE);
    }

    public PermissionException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
        setCode(DEFAULT_CODE);
    }
}
