package com.crm.register.application;

import com.crm.infrastructure.entity.Contact;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.ContactRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.ClientApplication;
import com.crm.register.application.contract.ContactApplication;
import com.crm.register.application.contract.ProviderApplication;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactApplicationImpl extends BaseModelServiceImpl<Contact> implements ContactApplication {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ClientApplication clientapplication;
    @Autowired
    private ProviderApplication providerApplication;


    @Override
    public Contact register(Contact contact) {
        return super.save(contact);
    }

    @Override
    public Contact register(Contact contact, Client client) {

        if (client.getId() == null || !clientapplication.getOne(client.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("contact.invalid.client"));
        }

        contact.setPerson(client.to());

        return super.save(contact);
    }

    @Override
    public Contact register(Contact contact, Provider provider) {
        if (provider.getId() == null || !providerApplication.getOne(provider.getId()).isPresent()) {
            throw new ValidationException(Sets.newHashSet("contact.invalid.provider"));
        }

        contact.setPerson(provider.to());

        return super.save(contact);
    }

    public List<Contact> getContactsByClient(Client client) {
        return contactRepository.findByPerson(client.to());
    }

    public List<Contact> getContactsByProvider(Provider provider) {
        return contactRepository.findByPerson(provider.to());
    }

    public BaseRepository<Contact, Long> getRepository() {
        return this.contactRepository;
    }
}
