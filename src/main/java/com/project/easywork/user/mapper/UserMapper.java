package com.project.easywork.user.mapper;

import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  
  @Mapping(target = "password", ignore = true)
  User toEntityForCreate(UserCreateDto dto);
  
  @Mapping(target = "teamId", source = "team.teamId")
  UserResponseDto toResponseDto(User user);
  
  List<UserResponseDto> toResponseDtoList(List<User> users);
}