package com.shop.dto.product;

import javax.validation.constraints.Min;
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
public class UpdateProductDTO {

    @Size(min = 3, max = 100, message = "Designation must be between 3 and 100 characters")
    private String designation;

    @Min(value = 0, message = "Price must be positive")
    private Double price;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    private Long categoryId; 
}
