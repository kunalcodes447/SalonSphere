package com.project.salonsphere.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateDTO {
	
	@NotBlank(message = "First name cannot be empty")
	@Size(min = 2,max = 50, message = "First name must have at least 2 characters")
	private String firstname;
	
	@NotBlank(message = "Last name cannot be empty")
	@Size(min = 2,max = 50, message = "Last name must have at least 2 characters")
	private String lastname;
	
	@Pattern(regexp = "^[0-9]{10}$",
			 message = "Phone number must contain exactly 10 digits")
	private String phoneno;
	
	@Size(min = 10, max = 255, message = "Address should be between 10 and 255 characters")
	private String address;
}
