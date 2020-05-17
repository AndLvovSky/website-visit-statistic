package com.andlvovsky.wvs.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryVisitsDto {
  private String country;
  private Integer visits;
}
