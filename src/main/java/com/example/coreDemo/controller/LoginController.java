package com.example.coreDemo.controller;

import com.example.coreDemo.exception.UserErrorCode;
import com.example.coreDemo.exception.UserException;
import com.example.coreDemo.payload.request.LoginRequest;
import com.example.coreDemo.payload.response.ApiResponse;
import com.example.coreDemo.payload.response.LoginResponse;
import com.example.coreDemo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getPhoneNumber(),
                                    request.getPassword()
                            )
                    );

            String token = jwtService.generateToken(
                    (UserDetails) authentication.getPrincipal()
            );

            return ApiResponse.<LoginResponse>builder()
                    .data(new LoginResponse(token))
                    .status("OK")
                    .build();

        } catch (BadCredentialsException exception) {
            throw new UserException(UserErrorCode.InvalidLogin);
        }
    }
}
