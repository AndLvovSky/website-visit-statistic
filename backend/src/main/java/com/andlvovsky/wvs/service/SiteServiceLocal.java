package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.entity.SiteEntity;

public interface SiteServiceLocal {
  SiteEntity findByApiKey(String apiKey);
}
