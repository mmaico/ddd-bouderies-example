package com.crm.timeline.infrastructure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class TimelineHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;




    @PostConstruct
    public void config() {
        resolver.getAttributesMap().put("timelineHelper", null);
    }
}
