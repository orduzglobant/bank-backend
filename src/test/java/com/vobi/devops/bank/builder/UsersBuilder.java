package com.vobi.devops.bank.builder;

import com.vobi.devops.bank.domain.Users;

public class UsersBuilder {
	
	private UsersBuilder() {}
	
	public static Users getUsers() {
		Users user=new Users();
		user.setEnable("Y");
		user.setName("Juan Perez");
		user.setToken("768234kbjasfjbda");
		user.setUserEmail("vondrusek1@wisc.edu");
		user.setUserType(UserTypeBuilder.getUserType());
		
		return user;
	}
	
	public static Users getUsersDisable() {
		Users user=getUsers();
		user.setEnable("N");
		return user;
	}

}
