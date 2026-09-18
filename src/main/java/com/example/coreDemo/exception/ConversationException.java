package com.example.coreDemo.exception;

public class ConversationException extends RuntimeException{
    private final String code;

    public ConversationException(ConversationErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
