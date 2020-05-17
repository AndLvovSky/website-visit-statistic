package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.CountryVisitsDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.CountryStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.COUNTRY_STATISTICS)
public class CountryStatisticsController {

  private final CountryStatisticsService countryStatisticsService;

  @GetMapping("/visits/week/{siteId}")
  public List<CountryVisitsDto> getVisitsPerCountryForTheLastWeek(@PathVariable Long siteId) {
    return countryStatisticsService.getVisitsPerCountryForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public List<CountryVisitsDto> getVisitsPerCountryForTheLastMonth(@PathVariable Long siteId) {
    return countryStatisticsService.getVisitsPerCountryForTheLastMonth(siteId);
  }
}
