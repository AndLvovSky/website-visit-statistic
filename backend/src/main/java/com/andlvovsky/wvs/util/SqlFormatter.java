package com.andlvovsky.wvs.util;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

public class SqlFormatter implements MessageFormattingStrategy {
  private final Formatter formatter = new BasicFormatterImpl();

  @Override
  public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
    return formatter.format(sql);
  }
}
