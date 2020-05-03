package com.andlvovsky.wvs.controller;

import com.andlvovsky.wvs.dto.VisitDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.VisitService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.VISIT)
public class VisitController {

  private final VisitService visitService;

  @PostMapping("/{apiKey}")
  @ResponseStatus(HttpStatus.CREATED)
  public void visit(
      @RequestBody VisitDto visit,
      @PathVariable String apiKey,
      HttpServletRequest request) {
    visitService.visit(visit, apiKey, request.getRemoteAddr());
  }
}
