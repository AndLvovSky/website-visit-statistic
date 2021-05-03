package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.dto.statistics.CountryVisitsDto;

import java.util.List;

public interface CountryStatisticsService {
  List<CountryVisitsDto> getVisitsPerCountryForTheLastWeek(Long siteId);

  List<CountryVisitsDto> getVisitsPerCountryForTheLastMonth(Long siteId);

  List<CountryVisitsDto> getVisitsPerCountry(Long siteId, String fromDate, String toDate);
}
