package com.crm.infrastructure.security.authentication;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.builders.AbstractBuilder;
import com.crm.infrastructure.entity.builders.UserBuilder;

import java.util.Set;



public class LoggedUserBuilder extends AbstractBuilder<LoggedUser> {

	public LoggedUserBuilder() {
		this.entity = new LoggedUser();
	}

	public LoggedUserBuilder(String login, User user, Set<String> roles) {
		this();
		this.entity.setLogin(login);
		this.withUser(user);

        roles.forEach(roleUnit -> this.entity.addRole(roleUnit));

	}
	
	public LoggedUserBuilder withUser(User user) {
		User baseData = UserBuilder.createUser(user.getId())
			.withLogin(user.getLogin())
				.withName(user.getName())
				.withLastname(user.getLastname()).build();
		
		this.entity.setUser(baseData);
		return this;
	}
	

	public static LoggedUserBuilder createLoggedUser(String login, User user, Set<String> roles) {
		return new LoggedUserBuilder(login, user, roles);
	}

	public static LoggedUserBuilder createLoggedUser() {
		return new LoggedUserBuilder();
	}
}
