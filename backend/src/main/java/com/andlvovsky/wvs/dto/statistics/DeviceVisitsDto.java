package com.andlvovsky.wvs.dto.statistics;

import com.andlvovsky.wvs.meta.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVisitsDto {
  private Device device;
  private Integer visits;
}
