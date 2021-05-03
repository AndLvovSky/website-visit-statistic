package com.andlvovsky.wvs.controller;

import com.andlvovsky.wvs.dto.UserRegistrationDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.USERS)
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody UserRegistrationDto user) {
    userService.register(user);
  }
}
