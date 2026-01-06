package com.project.easywork.user.mapper;

import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface UserMapper {
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "team", ignore = true)
  User toEntity(UserCreateDto dto);
  
  @Mapping(target = "teamId", source = "team.id")
  UserResponseDto toDto(User user);
  
  List<UserResponseDto> toDtoList(List<User> users);
}