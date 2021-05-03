package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.VisitSummaryDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.VisitSummaryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.VISIT_SUMMARY_STATISTICS)
public class VisitSummaryController {

  private final VisitSummaryService visitSummaryService;

  @GetMapping("/visits/week/{siteId}")
  public VisitSummaryDto getVisitSummaryForTheLastWeek(@PathVariable Long siteId) {
    return visitSummaryService.getVisitSummaryForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public VisitSummaryDto getVisitSummaryForTheLastMonth(@PathVariable Long siteId) {
    return visitSummaryService.getVisitSummaryForTheLastMonth(siteId);
  }

  @GetMapping("/visits/custom/{siteId}")
  public VisitSummaryDto getVisitSummary(
      @PathVariable Long siteId,
      @RequestParam String fromDate,
      @RequestParam String toDate
  ) {
    return visitSummaryService.getVisitSummary(siteId, fromDate, toDate);
  }
}
