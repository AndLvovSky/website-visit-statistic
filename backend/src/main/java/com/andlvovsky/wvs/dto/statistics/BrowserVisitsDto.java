package com.andlvovsky.wvs.dto.statistics;

import com.andlvovsky.wvs.meta.Browser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserVisitsDto {
  private Browser browser;
  private Integer visits;
}
