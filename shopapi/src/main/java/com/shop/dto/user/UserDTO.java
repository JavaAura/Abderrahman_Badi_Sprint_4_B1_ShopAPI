package com.shop.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shop.dto.role.RoleDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    private Long id;
    
    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 32, message = "Username must be between 6 and 32 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    private String password;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    private String repeatPassword;

    @NotNull(message = "Email cannot be null")
    @Size(min = 8, max = 32, message = "Email must be between 8 and 32 characters")
    @Email(message = "Error in email format")
    private String email;
        
    @NotNull(message = "Role not provided")
    private RoleDTO role;

}
