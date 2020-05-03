package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.meta.Device;
import com.andlvovsky.wvs.service.DeviceAnalyzer;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DefaultDeviceAnalyzer implements DeviceAnalyzer {

  private static final Pattern MOBILE_PATTERN = Pattern.compile("Mobi|Android");

  @Override
  public Device getDevice(String userAgent) {
    Matcher matcher = MOBILE_PATTERN.matcher(userAgent);
    return matcher.find() ? Device.MOBILE : Device.PC;
  }
}
