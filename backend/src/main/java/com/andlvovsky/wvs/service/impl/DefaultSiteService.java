package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.dto.SiteKey;
import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.mapper.SiteMapper;
import com.andlvovsky.wvs.repository.SiteRepository;
import com.andlvovsky.wvs.service.ApiKeyServiceLocal;
import com.andlvovsky.wvs.service.SiteService;
import com.andlvovsky.wvs.service.UserServiceLocal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultSiteService implements SiteService {

  private final SiteRepository siteRepository;
  private final SiteMapper siteMapper;
  private final UserServiceLocal userServiceLocal;
  private final ApiKeyServiceLocal apiKeyServiceLocal;

  @Override
  @Transactional
  public SiteKey createSite(NewSiteDto site) {
    SiteEntity siteEntity = siteMapper.toEntity(site);
    siteEntity.setUser(userServiceLocal.getCurrentUser());
    siteEntity = siteRepository.save(siteEntity);
    apiKeyServiceLocal.createApiKey(siteEntity);
    return new SiteKey(siteEntity.getId());
  }
}
