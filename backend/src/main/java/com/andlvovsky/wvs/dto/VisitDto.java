package com.andlvovsky.wvs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VisitDto {
  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy, HH:mm:ss")
  private LocalDateTime time;
  @NotNull
  private String userAgent;
  private String referralWebsite;
}
