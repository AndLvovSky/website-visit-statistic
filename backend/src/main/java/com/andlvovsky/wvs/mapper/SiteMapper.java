package com.andlvovsky.wvs.mapper;

import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.entity.SiteEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SiteMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "createdOn", ignore = true)
  SiteEntity toEntity(NewSiteDto dto);
}
