package com.example.coreDemo.exception;

public class UserException extends RuntimeException{
    private final String code;

    public UserException(UserErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
