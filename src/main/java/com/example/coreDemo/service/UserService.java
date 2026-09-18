package com.example.coreDemo.service;

import com.example.coreDemo.entity.UserEntity;
import com.example.coreDemo.exception.UserException;
import com.example.coreDemo.exception.UserErrorCode;
import com.example.coreDemo.helper.UserHelper;
import com.example.coreDemo.mapper.UserMapper;
import com.example.coreDemo.payload.request.CreateUserRequest;
import com.example.coreDemo.payload.response.ApiResponse;
import com.example.coreDemo.payload.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserHelper userHelper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ApiResponse<UserResponse> createUser(CreateUserRequest request) {

        if (userHelper.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new UserException(UserErrorCode.PhoneNumberAlreadyExists);
        }

        UserEntity user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userHelper.save(user);

        return ApiResponse.<UserResponse>builder()
                .status("OK")
                .data(userMapper.toUserResponse(user))
                .build();
    }

}
