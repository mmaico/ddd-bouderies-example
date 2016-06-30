package com.crm.delivery.application;

import com.crm.delivery.infrastructure.validators.WorkspaceUnitValidator;
import com.crm.infrastructure.entity.WorkspaceUnit;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.sale.SalesOrder;
import com.crm.infrastructure.repository.WorkspaceUnitRepository;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.crm.infrastructure.validators.ValidatorHelper.hasContraintViolated;

@Service
public class WorkspaceApplicationImpl extends BaseModelServiceImpl<WorkspaceUnit> implements WorkspaceApplication {

    @Autowired
    private WorkspaceUnitRepository repository;

    @Autowired
    private WorkspaceUnitValidator validator;

    public WorkspaceUnit register(WorkspaceUnit workspaceUnit) {

        hasContraintViolated(workspaceUnit, validator);

        Optional<WorkspaceUnit> result = repository.findBySalesOrderAndUser(workspaceUnit.getSalesOrder(), workspaceUnit.getUser());

        if (!result.isPresent()) {
            return super.save(workspaceUnit);
        }

        return result.get();
    }

    @Override
    public List<User> findUsersResponsibles(SalesOrder salesOrder) {
        if (salesOrder == null || salesOrder.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findUserWithItemsInWorkspace(salesOrder);
    }

    @Override
    public Boolean isInMyWorkspace(SalesOrder salesOrder, User user) {
        if (salesOrder == null || salesOrder.isNew() || user == null || user.isNew()) {
            return Boolean.FALSE;
        }
        return repository.findBySalesOrderAndUser(salesOrder, user).isPresent();
    }

    @Override
    public void removeItemWorkspaceBy(SalesOrder salesOrder, User user) {
        Optional<WorkspaceUnit> result = repository.findBySalesOrderAndUser(salesOrder, user);

        if (result.isPresent()) {
            repository.delete(result.get());
        }
    }


    public BaseRepository<WorkspaceUnit, Long> getRepository() {
        return this.repository;
    }

    @Override
    public List<SalesOrder> findNewSalesOrder() {
        return this.repository.findSalesOrderOutActDelivery();
    }

    @Override
    public List<SalesOrder> findBy(User user) {
        return this.repository.findByUser(user);
    }

    @Override
    public List<SalesOrder> findSalesOrderNotInWorkspace() {
        return this.repository.findSalesOrderNotInWorkspace();
    }
}
