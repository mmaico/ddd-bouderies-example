package com.crm.register.infrastructure.helpers;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.repository.Pager;
import com.crm.register.application.contract.UserApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellersHelper {

    @Autowired
    private UserApplication application;

    public Iterable<User> getAllSellers() {
        return application.findAll(Pager.build().withPageSize(1000));
    }
}
