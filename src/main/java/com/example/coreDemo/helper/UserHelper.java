package com.example.coreDemo.helper;

import com.example.coreDemo.entity.UserEntity;
import com.example.coreDemo.exception.UserException;
import com.example.coreDemo.exception.UserErrorCode;
import com.example.coreDemo.repository.UserRepository;
import com.example.coreDemo.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Long userId = userDetails.getUser().getId();

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.UserNotFound));
    }

    public UserEntity findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserException(UserErrorCode.UserNotFound));
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
