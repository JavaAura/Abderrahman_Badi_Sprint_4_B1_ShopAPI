package com.shop.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shop.dto.role.RoleDTO;
import com.shop.dto.user.UserDTO;
import com.shop.exceptions.InvalidDataException;
import com.shop.model.Role;
import com.shop.model.User;

@Component
public class UserMapper {
    private final List<String> VALID_INCLUDES = Arrays.asList("role");

    public void verifyIncludes(String... with)
            throws InvalidDataException {
        List<String> includesList = Arrays.asList(with);

        for (String include : includesList) {
            if (!include.isEmpty() && !VALID_INCLUDES.contains(include)) {
                throw new InvalidDataException("Invalid include: " + include);
            }
        }
    }

    public User convertToEntity(UserDTO userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .role(Role.builder().id(userDTO.getRoleId()).build())
                .build();
    }

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

    public List<UserDTO> convertToDTOList(List<User> users) {
        return users.stream()
                .map(user -> convertToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDTO convertToDTO(User user, String... with) {
        List<String> includesList = Arrays.asList(with);

        RoleDTO roleDTO = null;

        if (includesList.contains("category")) {
            Role role = user.getRole();
            roleDTO = RoleDTO.builder()
                    .name(role.getName())
                    .build();
        }

        return UserDTO.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(roleDTO)
                .build();
    }

    public List<UserDTO> convertToDTOList(List<User> users, String... with) {
        return users.stream()
                .map(user -> convertToDTO(user, with))
                .collect(Collectors.toList());
    }
}
