package com.techelevator.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Survey {
	
	@NotBlank(message="Required!")
	private String park;
	
	@NotBlank(message="Required!")
	@Email(message="Email must be valid!")
	private String email;
	
	@NotBlank(message="Required!")
	private String state;
	
	@NotBlank(message="Required!")
	private String level;
	
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
