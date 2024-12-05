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
import com.shop.service.interfaces.RoleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final List<String> VALID_INCLUDES = Arrays.asList("role");

    private final RoleService roleService;

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
        Role role = roleService.getRoleById(userDTO.getRole().getId());

        return User.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(role)
                .build();
    }

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
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

        if (includesList.contains("role")) {
            Role role = user.getRole();
            roleDTO = RoleDTO.builder()
                    .name(role.getName())
                    .build();
        }

        return UserDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .role(roleDTO)
                .build();
    }

    public List<UserDTO> convertToDTOList(List<User> users, String... with) {
        return users.stream()
                .map(user -> convertToDTO(user, with))
                .collect(Collectors.toList());
    }
}
