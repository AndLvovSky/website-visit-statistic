package com.andlvovsky.wvs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @PostMapping("/login")
  public ResponseEntity<Void> login() {
    return ResponseEntity.ok().build();
  }
}
