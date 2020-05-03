package com.andlvovsky.wvs.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewSiteDto {
  @NotNull
  private String name;
  @NotNull
  private String link;
}
