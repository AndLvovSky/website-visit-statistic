package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.dto.VisitDto;

public interface VisitService {
  void visit(VisitDto visit, String apiKey);
}
