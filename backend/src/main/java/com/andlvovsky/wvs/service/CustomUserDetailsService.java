package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.entity.UserEntity;
import com.andlvovsky.wvs.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(this::getUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
  }

  private UserDetails getUserDetails(UserEntity user) {
    return User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .authorities(Collections.emptyList())
        .build();
  }
}
