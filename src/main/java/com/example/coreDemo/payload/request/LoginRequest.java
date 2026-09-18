package com.example.coreDemo.payload.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
