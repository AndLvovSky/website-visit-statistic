package com.andlvovsky.wvs.meta;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoint {
  public static final String LOGIN = "/login";
  public static final String SITE = "/site";
  public static final String VISIT = "/visit";
  public static final String STATISTICS = "/statistics";
  public static final String TIME_STATISTICS = STATISTICS + "/time";
}
