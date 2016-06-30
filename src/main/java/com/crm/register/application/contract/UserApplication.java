package com.crm.register.application.contract;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.service.ModelService;


public interface UserApplication extends ModelService<User> {

    User register(User user);

    Boolean existsByLogin(String login);
}
