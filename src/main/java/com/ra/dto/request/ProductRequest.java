package com.ra.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
	@NotNull(message = "name must be not null")
	@NotEmpty(message = "name must be not empty")
	@NotBlank(message = "name must be not blank")
	private String name;
	@NotNull(message = "price must be not null")
	@Min(value = 0, message = "price must be than 0")
	private Double price;
	@NotNull(message = "stock must be not null")
	@Min(value = 0, message = "stock must be than 0")
	private Double stock;
	private Boolean status;
}
