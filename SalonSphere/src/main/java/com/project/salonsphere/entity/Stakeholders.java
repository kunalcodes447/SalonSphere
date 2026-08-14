package com.project.salonsphere.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.project.salonsphere.emums.Role;
import com.project.salonsphere.emums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Stakeholders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "First name cannot be empty")
	@Size(min = 2,max = 50, message = "First name must have at least 2 characters")
	private String firstname;
	
	@NotBlank(message = "Last name cannot be empty")
	@Size(min = 2,max = 50, message = "Last name must have at least 2 characters")
	private String lastname;
	
	@Column(unique = true)
	private String email;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@#$%^&+=!]).*$",
			 message = "Password must contain uppercase, lowercase, number and special character")
	private String password;
	
	@Pattern(regexp = "^[0-9]{10}$",
			 message = "Phone number must contain exactly 10 digits")
	private String phoneno;
	
	@Size(min = 10, max = 255, message = "Address should be between 10 and 255 characters")
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
}
