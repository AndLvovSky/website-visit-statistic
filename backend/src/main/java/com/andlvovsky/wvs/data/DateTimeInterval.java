package com.andlvovsky.wvs.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DateTimeInterval {
  private final LocalDateTime start;
  private final LocalDateTime end;
}
