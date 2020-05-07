package com.andlvovsky.wvs.dto;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullSiteDto {
  private String id;
  private String name;
  private String link;
  private ZonedDateTime createdOn;
  private String apiKey;
}
