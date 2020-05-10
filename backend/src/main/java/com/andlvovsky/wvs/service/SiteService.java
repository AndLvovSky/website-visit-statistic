package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.dto.FullSiteDto;
import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.dto.SiteDto;
import com.andlvovsky.wvs.dto.SiteKey;

import java.util.List;

public interface SiteService {
  SiteKey createSite(NewSiteDto site);

  List<SiteDto> getAll();

  FullSiteDto get(Long siteId);

  FullSiteDto update(FullSiteDto site);
}
