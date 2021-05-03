package com.andlvovsky.wvs.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationDto {
  private String username;
  private String email;
  private String fullName;
  private String password;
}
