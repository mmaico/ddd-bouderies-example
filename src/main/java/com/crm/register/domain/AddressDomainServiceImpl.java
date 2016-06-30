package com.crm.register.domain;

import com.crm.infrastructure.entity.Address;
import com.crm.infrastructure.entity.location.Country;
import com.crm.register.domain.contract.AddressDomainService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDomainServiceImpl implements AddressDomainService {


    @Override
    public void prepareToSave(List<Address> addresses) {

        if (CollectionUtils.isEmpty(addresses)) {
            return;
        }
        addresses.forEach(e -> prepareToSave(e));
    }

    @Override
    public void prepareToSave(Address address) {

        if (!Country.BRASIL.equals(address.getCountry().getId())) {
            address.setCity(null);
        }
    }
}
