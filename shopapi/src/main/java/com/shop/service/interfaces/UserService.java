package com.shop.service.interfaces;

import java.util.List;

import com.shop.dto.user.UserDTO;

/**
 * Service interface for User entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface UserService {

   UserDTO getUserById(long id);

   UserDTO getUserById(long id, String... with);

   List<UserDTO> getAllUsers();

   List<UserDTO> getAllUsers(String... with);

   UserDTO addUser(UserDTO User);

   public UserDTO updateUser(long UserId, UserDTO User);

   public void deleteUserById(long UserId);

}
