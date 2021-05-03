package com.andlvovsky.wvs.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbVisitsDto {
  private String version;
  private Integer visits;
}
