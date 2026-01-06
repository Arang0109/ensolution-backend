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
  @Mapping(ignore = true, target = "password")
  @Mapping(ignore = true, target = "team")
  User toEntity(UserCreateDto dto);
  
  @Mapping(source = "team.id", target = "teamId")
  @Mapping(source = "team.name", target = "teamName")
  UserResponseDto toDto(User user);
  
  List<UserResponseDto> toDtoList(List<User> users);
}