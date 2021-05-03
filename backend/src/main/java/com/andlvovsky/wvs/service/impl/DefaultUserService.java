package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.dto.UserRegistrationDto;
import com.andlvovsky.wvs.entity.UserEntity;
import com.andlvovsky.wvs.mapper.UserMapper;
import com.andlvovsky.wvs.repository.UserRepository;
import com.andlvovsky.wvs.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void register(UserRegistrationDto user) {
    UserEntity userEntity = userMapper.toEntity(user);
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    userEntity.setPassword(encodedPassword);
    userRepository.save(userEntity);
  }
}
