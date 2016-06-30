package com.crm.auditing.infrastructure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class AuditingHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;


    @PostConstruct
    public void config() {



    }
}
