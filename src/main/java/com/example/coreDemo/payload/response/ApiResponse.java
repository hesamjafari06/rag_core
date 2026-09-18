package com.example.coreDemo.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

    private String status;
    private T data;
    @Builder.Default
    private Instant timestamp = Instant.now();

}