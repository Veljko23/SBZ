package com.app.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Admin extends User{

	private static final long serialVersionUID = 1L;
	
	public Admin() {}

}
