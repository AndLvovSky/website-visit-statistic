package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.entity.UserEntity;
import com.andlvovsky.wvs.repository.UserRepository;
import com.andlvovsky.wvs.service.UserServiceLocal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserServiceLocal implements UserServiceLocal {

  private final UserRepository userRepository;

  @Override
  public UserEntity getCurrentUser() {
    String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    return userRepository.getByUsername(username);
  }
}
