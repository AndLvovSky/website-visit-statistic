package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.BrowserVisitsDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.BrowserStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.BROWSER_STATISTICS)
public class BrowserStatisticsController {

  private final BrowserStatisticsService browserStatisticsService;

  @GetMapping("/visits/week/{siteId}")
  public List<BrowserVisitsDto> getVisitsPerBrowserForTheLastWeek(@PathVariable Long siteId) {
    return browserStatisticsService.getVisitsPerBrowserForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public List<BrowserVisitsDto> getVisitsPerBrowserForTheLastMonth(@PathVariable Long siteId) {
    return browserStatisticsService.getVisitsPerBrowserForTheLastMonth(siteId);
  }

  @GetMapping("/visits/custom/{siteId}")
  public List<BrowserVisitsDto> getVisitsPerBrowser(
      @PathVariable Long siteId,
      @RequestParam String fromDate,
      @RequestParam String toDate
  ) {
    return browserStatisticsService.getVisitsPerBrowser(siteId, fromDate, toDate);
  }
}
