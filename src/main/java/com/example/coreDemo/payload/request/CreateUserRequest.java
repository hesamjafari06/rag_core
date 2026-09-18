package com.example.coreDemo.payload.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;
    private String phoneNumber;
    private String password;
}
