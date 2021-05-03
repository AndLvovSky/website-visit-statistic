package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.AbVisitsDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.AbStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.AB_STATISTICS)
public class AbStatisticsController {

  private final AbStatisticsService abStatisticsService;

  @GetMapping("/visits/week/{siteId}")
  public List<AbVisitsDto> getVisitsPerWebsiteVersionForTheLastWeek(@PathVariable Long siteId) {
    return abStatisticsService.getVisitsPerWebsiteVersionForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public List<AbVisitsDto> getVisitsPerWebsiteVersionForTheLastMonth(@PathVariable Long siteId) {
    return abStatisticsService.getVisitsPerWebsiteVersionForTheLastMonth(siteId);
  }
}
