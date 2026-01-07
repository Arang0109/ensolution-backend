package com.project.easywork.user.mapper;

import com.project.easywork.user.domain.dto.UserCreateD;
import com.project.easywork.user.domain.dto.UserD;
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
  User toEntity(UserCreateD dto);
  
  @Mapping(source = "team.id", target = "teamId")
  @Mapping(source = "team.name", target = "teamName")
  UserD toDto(User user);
  
  List<UserD> toDtoList(List<User> users);
}