package com.shop.dto.role;

import javax.validation.constraints.NotNull;
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
public class RoleDTO {

    @NotNull(message = "Role name cannot be null")
    @Size(min = 3, max = 100, message = "Role name must be between 3 and 100 characters")
    private String name;

}
