package com.crm.infrastructure.helpers;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {


	@Autowired
	private UserRepository repository;

	public Iterable<User> findAll() {
		return repository.findAll();
	}


}
