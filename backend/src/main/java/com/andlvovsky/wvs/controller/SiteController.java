package com.andlvovsky.wvs.controller;

import com.andlvovsky.wvs.dto.FullSiteDto;
import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.dto.SiteDto;
import com.andlvovsky.wvs.dto.SiteKey;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.SiteService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.SITES)
public class SiteController {

  private final SiteService siteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SiteKey create(@RequestBody NewSiteDto site) {
    return siteService.createSite(site);
  }

  @GetMapping
  public List<SiteDto> getAll() {
    return siteService.getAll();
  }

  @GetMapping("/{siteId}")
  public FullSiteDto get(@PathVariable Long siteId) {
    return siteService.get(siteId);
  }
}
