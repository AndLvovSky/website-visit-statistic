package com.andlvovsky.wvs.service.statistics;

import com.andlvovsky.wvs.data.DateTimeInterval;

public interface DateTimeService {
  DateTimeInterval getLastWeekInterval();

  DateTimeInterval getLastMonthInterval();

  DateTimeInterval getInterval(String fromDate, String toDate);
}
