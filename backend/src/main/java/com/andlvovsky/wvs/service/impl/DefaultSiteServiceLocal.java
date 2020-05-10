package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.repository.SiteRepository;
import com.andlvovsky.wvs.service.SiteServiceLocal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultSiteServiceLocal implements SiteServiceLocal {

  private final SiteRepository siteRepository;

  @Override
  public SiteEntity findByApiKey(String apiKey) {
    return siteRepository.findByApiKey(apiKey)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Api key %s is not valid", apiKey)));
  }

  @Override
  public SiteEntity get(Long id) {
    return siteRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(String.format("Site with id %d not found", id)));
  }
}
