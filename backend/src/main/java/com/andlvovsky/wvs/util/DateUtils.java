package com.andlvovsky.wvs.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {
  private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static LocalDate mapToDate(String date) {
    return date == null ? null : LocalDate.parse(date, dateFormatter);
  }
}
