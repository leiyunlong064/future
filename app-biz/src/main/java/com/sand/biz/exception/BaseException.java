package com.sand.biz.exception;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;

public class BaseException extends RuntimeException {
    private static final String DEFAULT_CODE = "base.error.unknown_error";

    private String id = UUID.randomUUID().toString();

    private String code = DEFAULT_CODE;

    private static String format(String pattern, Object[] args) {
        if (StringUtils.isEmpty(pattern)) {
            return DEFAULT_CODE;
        }

        if (args == null || args.length == 0) {
            return pattern;
        }

        StringBuilder sb = new StringBuilder();

        int index = 0;
        for (String part : pattern.split("\\{ *\\}", -1)) {
            if (index != 0) {
                sb.append("{");
                sb.append(index - 1);
                sb.append("}");
            }
            sb.append(part);
            ++index;
        }

        return MessageFormat.format(sb.toString(), args);
    }

    protected BaseException() {
        super(DEFAULT_CODE);
    }

    protected BaseException(String message, Object... args) {
        super(format(message, args));
    }

    protected BaseException(Throwable cause) {
        super(DEFAULT_CODE, cause);
    }

    protected BaseException(Throwable cause, String message, Object... args) {
        super(format(message, args), cause);
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    protected void setCode(String code) {
        this.code = code;
    }
}
