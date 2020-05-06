package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.repository.VisitRepository;
import com.andlvovsky.wvs.service.VisitServiceLocal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultVisitServiceLocal implements VisitServiceLocal {

  private final VisitRepository visitRepository;

  @Override
  public List<VisitEntity> getVisits(Long siteId, DateTimeInterval interval) {
    return visitRepository.findBySiteIdAndTimeBetween(siteId, interval.getStart(), interval.getEnd());
  }
}
