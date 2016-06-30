package com.crm.register.application;

import com.crm.infrastructure.entity.Address;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.AddressRepository;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.AddressApplication;
import com.crm.register.application.contract.ClientApplication;
import com.crm.register.application.contract.ProviderApplication;
import com.crm.register.domain.contract.AddressDomainService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressApplicationImpl extends BaseModelServiceImpl<Address> implements AddressApplication {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientApplication clientApplication;

    @Autowired
    private ProviderApplication providerApplication;

    @Autowired
    private AddressDomainService service;


    @Override
    public Address register(Address address, Client client) {

        if (client.getId() == null || !clientApplication.getOne(client.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("address.invalid.client"));
        }

        address.setPerson(client.to());
        service.prepareToSave(address);

        return super.save(address);
    }

    @Override
    public Address register(Address address, Provider provider) {
        if (provider.getId() == null || !providerApplication.getOne(provider.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("address.invalid.provider"));
        }

        address.setPerson(provider.to());
        service.prepareToSave(address);

        return super.save(address);
    }

    @Override
    public List<Address> getAddressesByClient(Client client) {
        return addressRepository.findByPerson(client.to());
    }

    @Override
    public List<Address> getAddressesByProvider(Provider provider) {
        return addressRepository.findByPerson(provider.to());
    }

    public BaseRepository<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
