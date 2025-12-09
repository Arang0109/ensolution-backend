package com.project.easywork.user.mapper;

import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  
  @Mapping(target = "teamId", source = "team.id")
  UserResponseDto toDto(User user);
  
  @Mapping(target = "password", ignore = true)
  User toEntity(UserCreateDto dto);
  
  List<UserResponseDto> toDtoList(List<User> users);
}