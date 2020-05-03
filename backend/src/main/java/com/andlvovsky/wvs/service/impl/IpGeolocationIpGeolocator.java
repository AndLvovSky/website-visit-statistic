package com.andlvovsky.wvs.service.impl;

import com.andlvovsky.wvs.dto.IpGeolocationDto;
import com.andlvovsky.wvs.service.IpGeolocator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IpGeolocationIpGeolocator implements IpGeolocator {

  private final RestTemplate restTemplate;
  @Value("${ipGeolocation.url}")
  private String url;
  @Value("${ipGeolocation.apiKey}")
  private String apiKey;

  @Override
  public Optional<String> getCountryByIp(String ip) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("apiKey", apiKey)
        .queryParam("ip", ip);
    IpGeolocationDto ipGeolocation = restTemplate.getForObject(builder.toUriString(), IpGeolocationDto.class);
    return ipGeolocation == null ? Optional.empty() : Optional.of(ipGeolocation.getCountry());
  }
}
