package com.andlvovsky.websitevisitstatistics.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/visit")
public class VisitController {
  @PostMapping
  public String visit(HttpServletRequest request) {
    return request.getRemoteAddr();
  }
}
