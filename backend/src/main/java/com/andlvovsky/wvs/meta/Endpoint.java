package com.andlvovsky.wvs.meta;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoint {
  public static final String LOGIN = "/login";
  public static final String USERS = "/users";
  public static final String SITES = "/sites";
  public static final String VISIT = "/visit";
  public static final String STATISTICS = "/statistics";
  public static final String TIME_STATISTICS = STATISTICS + "/time";
  public static final String DEVICE_STATISTICS = STATISTICS + "/device";
  public static final String COUNTRY_STATISTICS = STATISTICS + "/country";
  public static final String REFERRAL_STATISTICS = STATISTICS + "/referral-websites";
  public static final String AB_STATISTICS = STATISTICS + "/a-b";
  public static final String ROUTE_STATISTICS = STATISTICS + "/route";
  public static final String VISIT_SUMMARY_STATISTICS = STATISTICS + "/summary";
  public static final String BROWSER_STATISTICS = STATISTICS + "/browser";
}
