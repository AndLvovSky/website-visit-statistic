package com.andlvovsky.wvs.service;

import java.util.Optional;

public interface IpGeolocator {
  Optional<String> getCountryByIp(String ip);
}
