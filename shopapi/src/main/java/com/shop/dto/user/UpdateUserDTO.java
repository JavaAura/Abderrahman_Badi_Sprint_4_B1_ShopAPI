package com.shop.dto.user;

import javax.validation.constraints.NotNull;

import com.shop.dto.role.RoleDTO;

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
    
    @NotNull(message = "Role shouldn't be null")
    private RoleDTO role; 
    
}
