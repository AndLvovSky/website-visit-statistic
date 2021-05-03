package com.andlvovsky.wvs.controller.statistics;

import com.andlvovsky.wvs.dto.statistics.ReferralWebsiteDto;
import com.andlvovsky.wvs.meta.Endpoint;
import com.andlvovsky.wvs.service.statistics.ReferralStatisticsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoint.REFERRAL_STATISTICS)
public class ReferralStatisticsController {

  private final ReferralStatisticsService referralStatisticsService;

  @GetMapping("/visits/week/{siteId}")
  public List<ReferralWebsiteDto> getReferralWebsitesForTheLastWeek(@PathVariable Long siteId) {
    return referralStatisticsService.getReferralWebsitesForTheLastWeek(siteId);
  }

  @GetMapping("/visits/month/{siteId}")
  public List<ReferralWebsiteDto> getReferralWebsitesForTheLastMonth(@PathVariable Long siteId) {
    return referralStatisticsService.getReferralWebsitesForTheLastMonth(siteId);
  }
}
