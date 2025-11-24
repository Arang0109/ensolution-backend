package com.project.easywork.auth.mapper;

import com.project.easywork.auth.domain.dto.UserCreateDto;
import com.project.easywork.auth.domain.dto.UserResponseDto;
import com.project.easywork.auth.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  
  @Mapping(target = "password", ignore = true)
  User toEntityForCreate(UserCreateDto dto);
  
  @Mapping(target = "teamName", source = "team.teamName")
  UserResponseDto toResponseDto(User user);
  
  List<UserResponseDto> toResponseDtoList(List<User> users);
}