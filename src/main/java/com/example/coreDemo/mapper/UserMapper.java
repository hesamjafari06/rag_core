package com.example.coreDemo.mapper;

import com.example.coreDemo.entity.UserEntity;
import com.example.coreDemo.payload.request.CreateUserRequest;
import com.example.coreDemo.payload.response.UserResponse;
import com.example.coreDemo.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {
    public UserDetails toUserDetails(UserEntity user){
        return new CustomUserDetails(user);
    }

    public UserEntity toEntity(CreateUserRequest request){

        return UserEntity.builder()
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .name(request.getName())
                .build();
    }

    public UserResponse toUserResponse(UserEntity user){

        return UserResponse.builder()
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .createdAt(
                        user.getCreatedAt().toString().substring(0, 10)
                )
                .build();
    }
}
