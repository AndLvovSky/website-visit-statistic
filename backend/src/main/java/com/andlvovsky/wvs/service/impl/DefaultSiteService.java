package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.dto.FullSiteDto;
import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.dto.SiteDto;
import com.andlvovsky.wvs.dto.SiteKey;
import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.mapper.SiteMapper;
import com.andlvovsky.wvs.repository.SiteRepository;
import com.andlvovsky.wvs.service.ApiKeyServiceLocal;
import com.andlvovsky.wvs.service.SiteService;
import com.andlvovsky.wvs.service.UserServiceLocal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.toList;

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

  @Override
  public List<SiteDto> getAll() {
    return siteRepository.findByUser(userServiceLocal.getCurrentUser()).stream()
        .map(siteMapper::toDto)
        .collect(toList());
  }

  @Override
  public FullSiteDto get(Long siteId) {
    SiteEntity siteEntity = siteRepository.findById(siteId)
        .orElseThrow(() -> new IllegalArgumentException("Site not found: " + siteId));
    FullSiteDto siteDto = siteMapper.toFullDto(siteEntity);
    String apiKey = apiKeyServiceLocal.getForSite(siteEntity);
    siteDto.setApiKey(apiKey);
    return siteDto;
  }
}
