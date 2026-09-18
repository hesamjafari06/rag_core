package com.example.coreDemo.security;

import com.example.coreDemo.helper.UserHelper;
import com.example.coreDemo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserHelper userHelper;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        return userMapper.toUserDetails(userHelper.findUserByPhoneNumber(phoneNumber));
    }
}
