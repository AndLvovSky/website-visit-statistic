package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.service.ApiKeyGenerator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultApiKeyGenerator implements ApiKeyGenerator {
  @Override
  public String generateApiKey() {
    return UUID.randomUUID().toString();
  }
}
