package com.andlvovsky.wvs.mapper;

import com.andlvovsky.wvs.dto.UserRegistrationDto;
import com.andlvovsky.wvs.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "password", ignore = true)
  UserEntity toEntity(UserRegistrationDto dto);
}
