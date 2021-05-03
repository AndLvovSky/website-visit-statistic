package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.dto.UserRegistrationDto;

public interface UserService {
  void register(UserRegistrationDto user);
}
