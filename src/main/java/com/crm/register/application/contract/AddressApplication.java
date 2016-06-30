package com.crm.register.application.contract;

import com.crm.infrastructure.entity.Address;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.service.ModelService;

import java.util.List;


public interface AddressApplication extends ModelService<Address> {


    Address register(Address address, Client client);

    Address register(Address address, Provider provider);

    List<Address> getAddressesByClient(Client client);

    List<Address> getAddressesByProvider(Provider provider);
}
