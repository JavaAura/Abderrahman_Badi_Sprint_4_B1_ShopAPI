package com.shop.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.user.UpdateUserDTO;
import com.shop.dto.user.UserDTO;

/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface UserService {

   UserDTO getUserById(long id);

   UserDTO getUserById(long id, String... with);

   public UserDTO getByUserName(String userName);

   Page<UserDTO> getAllUsers(Pageable pageable);

   Page<UserDTO> getAllUsers(Pageable pageable, String... with);

   UserDTO addUser(UserDTO User);

   public UserDTO updateUser(long UserId, UpdateUserDTO User, String... with);

   public void deleteUserById(long UserId);
}
