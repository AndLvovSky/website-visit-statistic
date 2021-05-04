package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.dto.VisitDto;
import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.meta.Browser;
import com.andlvovsky.wvs.meta.Device;
import com.andlvovsky.wvs.repository.VisitRepository;
import com.andlvovsky.wvs.service.DeviceAnalyzer;
import com.andlvovsky.wvs.service.IpGeolocator;
import com.andlvovsky.wvs.service.SiteServiceLocal;
import com.andlvovsky.wvs.service.VisitService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultVisitService implements VisitService {

  private static final String FALLBACK_COUNTRY = "US";
  private static final String LOOPBACK_ADDRESS = "0:0:0:0:0:0:0:1";

  private final VisitRepository visitRepository;
  private final SiteServiceLocal siteServiceLocal;
  private final DeviceAnalyzer deviceAnalyzer;
  private final IpGeolocator ipGeolocator;

  @Override
  @Transactional
  public void visit(VisitDto visit, String apiKey, String ip) {
    VisitEntity visitEntity = new VisitEntity();
    SiteEntity site = siteServiceLocal.findByApiKey(apiKey);
    visitEntity.setSite(site);
    visitEntity.setTime(visit.getTime());
    Device device = deviceAnalyzer.getDevice(visit.getUserAgent());
    visitEntity.setDevice(device);
    visitEntity.setIp(ip);
    if (!LOOPBACK_ADDRESS.equals(ip)) {
      String country = ipGeolocator.getCountryByIp(ip).orElse(FALLBACK_COUNTRY);
      visitEntity.setCountry(country);
    } else {
      visitEntity.setCountry(FALLBACK_COUNTRY);
    }
    visitEntity.setReferralWebsite(visit.getReferralWebsite());
    visitEntity.setWebsiteVersion(visit.getWebsiteVersion());
    visitEntity.setPath(visit.getPath());
    Browser browser = deviceAnalyzer.getBrowser(visit.getUserAgent());
    visitEntity.setBrowser(browser);
    visitRepository.save(visitEntity);
  }
}
