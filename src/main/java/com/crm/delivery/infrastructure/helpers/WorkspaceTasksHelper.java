package com.crm.delivery.infrastructure.helpers;


import com.crm.delivery.application.WorkspaceApplication;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkspaceTasksHelper {

    @Autowired
    private WorkspaceApplication workspaceApplication;

    @Autowired
    private SecurityHelper security;

    public List<SalesOrder> getSalesOrderInMyWorkspace() {
        User user = security.getPrincipal().getUser();
        return workspaceApplication.findBy(user);
    }

    public Boolean isInMyWorkspace(SalesOrder salesOrder) {
        User user = security.getPrincipal().getUser();
        return workspaceApplication.isInMyWorkspace(salesOrder, user);
    }

}
