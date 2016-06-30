package com.crm.register.domain.contract;


import com.crm.infrastructure.entity.Address;

import java.util.List;

public interface AddressDomainService {

    void prepareToSave(List<Address> addresses);

    void prepareToSave(Address address);
}
