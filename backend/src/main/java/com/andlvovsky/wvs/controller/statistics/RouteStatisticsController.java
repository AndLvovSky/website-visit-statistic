package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.RouteVisitsDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.RouteStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.ROUTE_STATISTICS)
public class RouteStatisticsController {

  private final RouteStatisticsService routeStatisticsService;

  @GetMapping("/visits/week/{siteId}")
  public List<RouteVisitsDto> getRouteVisitsForTheLastWeek(@PathVariable Long siteId) {
    return routeStatisticsService.getRouteVisitsForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public List<RouteVisitsDto> getRouteVisitsForTheLastMonth(@PathVariable Long siteId) {
    return routeStatisticsService.getRouteVisitsForTheLastMonth(siteId);
  }

  @GetMapping("/visits/custom/{siteId}")
  public List<RouteVisitsDto> getRouteVisits(
      @PathVariable Long siteId,
      @RequestParam String fromDate,
      @RequestParam String toDate
  ) {
    return routeStatisticsService.getRouteVisits(siteId, fromDate, toDate);
  }
}
