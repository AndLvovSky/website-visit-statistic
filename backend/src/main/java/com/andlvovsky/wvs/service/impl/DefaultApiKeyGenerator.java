package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.service.ApiKeyGenerator;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultApiKeyGenerator implements ApiKeyGenerator {
  @Override
  public String generateApiKey() {
    return UUID.randomUUID().toString();
  }
}
