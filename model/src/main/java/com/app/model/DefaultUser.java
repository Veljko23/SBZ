package com.app.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("U")
public class DefaultUser extends User{

	private static final long serialVersionUID = 1L;
	
	public DefaultUser() {}

}
