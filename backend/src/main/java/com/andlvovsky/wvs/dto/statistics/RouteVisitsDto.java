package com.andlvovsky.wvs.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteVisitsDto {
  private String path;
  private Integer visits;
}
