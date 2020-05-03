package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.dto.SiteKey;

public interface SiteService {
  SiteKey createSite(NewSiteDto site);
}
