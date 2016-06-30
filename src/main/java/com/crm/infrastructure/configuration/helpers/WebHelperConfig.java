package com.crm.infrastructure.configuration.helpers;


import com.crm.infrastructure.security.helpers.SecurityHelper;
import com.crm.infrastructure.helpers.*;
import org.apache.velocity.tools.generic.EscapeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;

@Component
public class WebHelperConfig {

    @Autowired
    private VelocityViewResolver resolver;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private LocationHelper locationHelper;

    @Autowired
    private MessagesI18nHelper messagesI18nHelper;

    @Autowired
    private UserHelper userHelper;

    @PostConstruct
    public void config() {

        resolver.getAttributesMap().put("moneyHelper", new FormatMoneyHelper());
        resolver.getAttributesMap().put("security", securityHelper);
        resolver.getAttributesMap().put("dateHelper", new DateHelper());
        resolver.getAttributesMap().put("locationHelper", locationHelper);
        resolver.getAttributesMap().put("messagei18n", messagesI18nHelper);
        resolver.getAttributesMap().put("userHelper", userHelper);
        resolver.getAttributesMap().put("esc", new EscapeTool());


    }
}
