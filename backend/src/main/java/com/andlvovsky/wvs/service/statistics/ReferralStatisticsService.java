package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.ReferralWebsiteDto;

import java.util.List;

public interface ReferralStatisticsService {
  List<ReferralWebsiteDto> getReferralWebsitesForTheLastWeek(Long siteId);

  List<ReferralWebsiteDto> getReferralWebsitesForTheLastMonth(Long siteId);
}
