package com.crm.delivery.application;

import com.crm.infrastructure.entity.WorkspaceUnit;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.service.ModelService;

import java.util.List;


public interface WorkspaceApplication extends ModelService<WorkspaceUnit> {

    List<SalesOrder> findNewSalesOrder();

    List<SalesOrder> findBy(User user);

    List<SalesOrder> findSalesOrderNotInWorkspace();

    WorkspaceUnit register(WorkspaceUnit workspaceUnit);

    List<User> findUsersResponsibles(SalesOrder salesOrder);

    Boolean isInMyWorkspace(SalesOrder salesOrder, User user);

    void removeItemWorkspaceBy(SalesOrder salesOrder, User user);
}
