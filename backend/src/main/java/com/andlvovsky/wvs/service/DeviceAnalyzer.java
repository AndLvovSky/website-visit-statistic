package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.meta.Browser;
import com.andlvovsky.wvs.meta.Device;

public interface DeviceAnalyzer {
  Device getDevice(String userAgent);

  Browser getBrowser(String userAgent);
}
