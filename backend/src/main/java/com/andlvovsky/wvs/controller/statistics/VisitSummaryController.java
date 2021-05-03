package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.VisitSummaryDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.VisitSummaryService;
import com.andlvovsky.wvs.util.HttpResponseFileSender;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.VISIT_SUMMARY_STATISTICS)
public class VisitSummaryController {

  private static final String VISIT_SUMMARY_FILE_NAME = "visit_summary.xlsx";

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

  @GetMapping("/visits/week/{siteId}/export")
  public void exportToExcelForTheLastWeek(@PathVariable Long siteId, HttpServletResponse response) throws IOException {
    File visitSummaryFile = visitSummaryService.exportVisitSummaryForTheLastWeekToExcel(siteId);
    HttpResponseFileSender.sendExcel(response, visitSummaryFile, VISIT_SUMMARY_FILE_NAME);
  }

  @GetMapping("/visits/month/{siteId}/export")
  public void exportToExcelForTheLastMonth(@PathVariable Long siteId, HttpServletResponse response) throws IOException {
    File visitSummaryFile = visitSummaryService.exportVisitSummaryForTheLastMonthToExcel(siteId);
    HttpResponseFileSender.sendExcel(response, visitSummaryFile, VISIT_SUMMARY_FILE_NAME);
  }

  @GetMapping("/visits/custom/{siteId}/export")
  public void exportToExcel(
      @PathVariable Long siteId,
      @RequestParam String fromDate,
      @RequestParam String toDate,
      HttpServletResponse response
  ) throws IOException {
    File visitSummaryFile = visitSummaryService.exportVisitSummaryToExcel(siteId, fromDate, toDate);
    HttpResponseFileSender.sendExcel(response, visitSummaryFile, VISIT_SUMMARY_FILE_NAME);
  }
}
