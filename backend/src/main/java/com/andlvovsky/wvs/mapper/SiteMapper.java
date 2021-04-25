package com.andlvovsky.wvs.mapper;

import com.andlvovsky.wvs.dto.FullSiteDto;
import com.andlvovsky.wvs.dto.NewSiteDto;
import com.andlvovsky.wvs.dto.SiteDto;
import com.andlvovsky.wvs.entity.SiteEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SiteMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "createdOn", ignore = true)
  SiteEntity toEntity(NewSiteDto dto);

  SiteDto toDto(SiteEntity entity);

  @Mapping(target = "apiKey", ignore = true)
  FullSiteDto toFullDto(SiteEntity entity);

  FullSiteDto toFullDto(SiteEntity entity, String apiKey);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdOn", ignore = true)
  @Mapping(target = "user", ignore = true)
  SiteEntity mergeToEntity(@MappingTarget SiteEntity entity, FullSiteDto dto);
}
