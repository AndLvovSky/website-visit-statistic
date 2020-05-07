package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.entity.ApiKeyEntity;
import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.repository.ApiKeyRepository;
import com.andlvovsky.wvs.service.ApiKeyGenerator;
import com.andlvovsky.wvs.service.ApiKeyServiceLocal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultApiKeyServiceLocal implements ApiKeyServiceLocal {

  private final ApiKeyGenerator apiKeyGenerator;
  private final ApiKeyRepository apiKeyRepository;

  @Override
  @Transactional
  public void createApiKey(SiteEntity site) {
    ApiKeyEntity apiKey = new ApiKeyEntity();
    apiKey.setSite(site);
    apiKey.setKey(apiKeyGenerator.generateApiKey());
    apiKeyRepository.save(apiKey);
  }

  @Override
  public String getForSite(SiteEntity site) {
    return apiKeyRepository.findBySite(site)
        .orElseThrow(() -> new IllegalArgumentException("Api key for site " + site.getId() + " not found"))
        .getKey();
  }
}
