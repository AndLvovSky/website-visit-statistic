package com.andlvovsky.wvs.data;

import java.time.DayOfWeek;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DayOfWeekVisits {
  private DayOfWeek dayOfWeek;
  private Integer visits;
}
