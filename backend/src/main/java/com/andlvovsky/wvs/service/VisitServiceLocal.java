package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.data.DateTimeInterval;
import com.andlvovsky.wvs.entity.VisitEntity;

import java.util.List;

public interface VisitServiceLocal {
  List<VisitEntity> getVisits(Long siteId, DateTimeInterval interval);
}
