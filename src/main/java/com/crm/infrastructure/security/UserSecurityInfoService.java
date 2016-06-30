package com.crm.infrastructure.security;


import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class UserSecurityInfoService {

    @Autowired
    private UserRepository repository;


    public Optional<User> getUser(String login, String password) {

        if (isBlank(login) || isBlank(password)) {
            return Optional.empty();
        }

        return  repository.findByLoginAndPassword(login, password);
    }

}
