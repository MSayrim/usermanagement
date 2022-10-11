package com.example.usermanagement.mapper;


import com.example.usermanagement.dto.constant.ShortUserDTO;
import com.example.usermanagement.dto.constant.UserDTO;
import com.example.usermanagement.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named("toEntity")
    User toEntity(UserDTO userDTO);

    @Named("toDTO")
    UserDTO toDTO(User user);

    @IterableMapping(qualifiedByName = "toEntity")
    List<User> toList(List<UserDTO> userDTOList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<UserDTO> toDTOList(List<User> userList);

    @Named("toShortDTO")
    ShortUserDTO toShortDTO(User user);

    @IterableMapping(qualifiedByName = "toShortDTO")
    List<ShortUserDTO> toShortDTOList(List<User> userList);

}