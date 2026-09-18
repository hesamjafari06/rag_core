package com.example.coreDemo.controller;

import com.example.coreDemo.exception.UserException;
import com.example.coreDemo.exception.UserErrorCode;
import com.example.coreDemo.payload.request.CreateUserRequest;
import com.example.coreDemo.payload.response.ApiResponse;
import com.example.coreDemo.payload.response.UserResponse;
import com.example.coreDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<Void> checkLogin(){

        Authentication currentAuthentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (currentAuthentication != null
                && currentAuthentication.isAuthenticated()
                && !(currentAuthentication instanceof AnonymousAuthenticationToken)) {

            throw new UserException(UserErrorCode.AlreadyAuthenticated);
        }

        return ApiResponse.<Void>builder()
                .status("OK")
                .build();
    }

    @PostMapping
    public ApiResponse<UserResponse> register(
            @RequestBody CreateUserRequest request
    ) {

        return userService.createUser(request);
    }
}
