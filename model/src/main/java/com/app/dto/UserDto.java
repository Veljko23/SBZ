package com.app.dto;

import com.app.model.User;

public class UserDto {
	private int id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String role;
	
	public UserDto() {}

	public UserDto(int id, String name, String surname, String username, String email, String role) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.role = role;
	}
	
	public UserDto(User user) {
		id = user.getId();
		name = user.getName();
		surname = user.getSurname();
		username = user.getUsername();
		email = user.getEmail();
		role = user.getRoles().get(0).getName().toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
