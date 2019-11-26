package com.sand.biz.exception;

public class LoginException extends BaseException {
    private static final String DEFAULT_CODE = "login.error";

    public LoginException() {
        super(DEFAULT_CODE);
        setCode(DEFAULT_CODE);
    }

    public LoginException(String message, Object... args) {
        super(message, args);
        setCode(DEFAULT_CODE);
    }

    public LoginException(Throwable cause) {
        super(cause, DEFAULT_CODE);
        setCode(DEFAULT_CODE);
    }

    public LoginException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
        setCode(DEFAULT_CODE);
    }
}
