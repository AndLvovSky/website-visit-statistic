package com.andlvovsky.wvs.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WvsAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    try {
      PrintWriter writer = response.getWriter();
      writer.println("HTTP Status 401 - " + authException.getMessage());
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override
  public void afterPropertiesSet() {
    setRealmName("Wvs");
    super.afterPropertiesSet();
  }
}
