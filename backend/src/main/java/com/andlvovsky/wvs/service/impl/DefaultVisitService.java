package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.dto.VisitDto;
import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.meta.Device;
import com.andlvovsky.wvs.repository.VisitRepository;
import com.andlvovsky.wvs.service.DeviceAnalyzer;
import com.andlvovsky.wvs.service.SiteServiceLocal;
import com.andlvovsky.wvs.service.VisitService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultVisitService implements VisitService {

  private final VisitRepository visitRepository;
  private final SiteServiceLocal siteServiceLocal;
  private final DeviceAnalyzer deviceAnalyzer;

  @Override
  @Transactional
  public void visit(VisitDto visit, String apiKey) {
    VisitEntity visitEntity = new VisitEntity();
    SiteEntity site = siteServiceLocal.findByApiKey(apiKey);
    visitEntity.setSite(site);
    visitEntity.setTime(visit.getTime());
    Device device = deviceAnalyzer.getDevice(visit.getUserAgent());
    visitEntity.setDevice(device);
    visitRepository.save(visitEntity);
  }
}
