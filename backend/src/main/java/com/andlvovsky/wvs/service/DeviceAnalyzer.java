package com.andlvovsky.wvs.service;

import com.andlvovsky.wvs.meta.Device;

public interface DeviceAnalyzer {
  Device getDevice(String userAgent);
}
