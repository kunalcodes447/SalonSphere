package com.project.salonsphere.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {
	
	@Column(unique = true)
	private String email;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@#$%^&+=!]).*$",
			 message = "Password must contain uppercase, lowercase, number and special character")
	private String password;
	
}
