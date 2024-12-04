package com.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.dto.user.UpdateUserDTO;
import com.shop.dto.user.UserDTO;
import com.shop.exceptions.ResourceNotFoundException;
import com.shop.mapper.UserMapper;
import com.shop.model.Role;
import com.shop.model.User;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.interfaces.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Service implementation for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Service
@Log4j2
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDTO getUserById(long id, String... with) {
        userMapper.verifyIncludes(with);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return userMapper.convertToDTO(user, with);
    }

    @Override
    public UserDTO getByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return userMapper.convertToDTO(user);
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable, String... with) {
        userMapper.verifyIncludes(with);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> userMapper.convertToDTO(user, with));
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> userMapper.convertToDTO(user));
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userMapper.convertToEntity(userDTO);
        log.info("User : " + user);
        return userMapper.convertToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(long userId, UpdateUserDTO userDTO, String... with) {
        userMapper.verifyIncludes(with);
        User userDB = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        if (userDTO.getRole() != null) {
            Role role = roleRepository.findById(userDTO.getRole().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            userDB.setRole(role);
        }

        return userMapper.convertToDTO(userRepository.save(userDB), with);
    }

    @Override
    public void deleteUserById(long UserId) throws ResourceNotFoundException {
        User User = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(User);
    }

}
