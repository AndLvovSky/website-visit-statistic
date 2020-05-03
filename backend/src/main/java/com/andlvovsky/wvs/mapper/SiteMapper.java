package com.andlvovsky.wvs.mapper;

import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.entity.SiteEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteMapper {
  SiteEntity toEntity(NewSiteDto dto);
}
