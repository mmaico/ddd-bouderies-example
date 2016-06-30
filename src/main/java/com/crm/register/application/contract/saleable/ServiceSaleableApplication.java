package com.crm.register.application.contract.saleable;

import com.crm.infrastructure.entity.saleable.Service;
import com.crm.infrastructure.service.ModelService;

public interface ServiceSaleableApplication extends ModelService<Service> {

    Service register(Service service);
}
