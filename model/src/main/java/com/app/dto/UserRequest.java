package com.app.dto;

import com.app.model.User;

public class UserRequest {
	
	private int id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
	
	public UserRequest() {
		super();
	}
	
	public UserRequest(User user) {
		id = user.getId();
		name = user.getName();
		surname = user.getSurname();
		username = user.getUsername();
		email = user.getEmail();
	}
	
	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setPassword(password);
		
		return user;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
