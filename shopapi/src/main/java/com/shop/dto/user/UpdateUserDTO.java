package com.shop.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserDTO {

    @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters")
    private String userName;

    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    private String password;

    @Size(min = 8, max = 32, message = "Email must be between 8 and 32 characters")
    @Email(message = "Error in email format")
    private String email;
    
}
