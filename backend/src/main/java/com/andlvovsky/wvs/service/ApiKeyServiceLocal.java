package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.entity.SiteEntity;

public interface ApiKeyServiceLocal {
  void createApiKey(SiteEntity site);

  String getForSite(SiteEntity site);
}
