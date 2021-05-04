package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.meta.Browser;
import com.andlvovsky.wvs.meta.Device;
import com.andlvovsky.wvs.service.DeviceAnalyzer;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DefaultDeviceAnalyzer implements DeviceAnalyzer {

  private static final Pattern MOBILE_PATTERN = Pattern.compile("Mobi|Android");

  private static final String CHROME_PATTERN = "Chrome";
  private static final String FIREFOX_PATTERN = "Firefox";
  private static final String SAFARI_PATTERN = "Safari";
  private static final String OPERA_PATTERN_OLD = "Opera";
  private static final String OPERA_PATTERN_NEW = "OPR";
  private static final String IE_PATTERN_OLD = "MSIE";
  private static final String IE_PATTERN_NEW = "Trident";

  @Override
  public Device getDevice(String userAgent) {
    Matcher matcher = MOBILE_PATTERN.matcher(userAgent);
    return matcher.find() ? Device.MOBILE : Device.PC;
  }

  @Override
  public Browser getBrowser(String userAgent) {
    if (userAgent.contains(CHROME_PATTERN)) {
      return Browser.CHROME;
    } else if (userAgent.contains(FIREFOX_PATTERN)) {
      return Browser.FIREFOX;
    } else if (userAgent.contains(SAFARI_PATTERN)) {
      return Browser.SAFARI;
    } else if (userAgent.contains(OPERA_PATTERN_OLD) || userAgent.contains(OPERA_PATTERN_NEW)) {
      return Browser.OPERA;
    } else if (userAgent.contains(IE_PATTERN_OLD) || userAgent.contains(IE_PATTERN_NEW)) {
      return Browser.IE;
    } else {
      return Browser.OTHER;
    }
  }
}
