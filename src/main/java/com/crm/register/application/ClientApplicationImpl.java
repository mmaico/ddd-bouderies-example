package com.crm.register.application;

import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.client.Client;
import com.crm.infrastructure.helpers.Filter;
import com.crm.infrastructure.helpers.FilterAggregator;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.PersonRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.ClientApplication;
import com.crm.register.domain.contract.ClientDomainService;
import com.crm.infrastructure.entity.enums.PersonProfilesEnum;
import com.crm.infrastructure.repository.predicates.PersonPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class ClientApplicationImpl extends BaseModelServiceImpl<Person> implements ClientApplication {

    private PersonRepository clientRepository;

    private ClientDomainService domainService;

    @Autowired
    public ClientApplicationImpl(PersonRepository clientRepository, ClientDomainService domainService) {
        this.clientRepository = clientRepository;
        this.domainService = domainService;
    }

    @Override
    public Client register(Client client) {

        return super.save(client.to(), domainService);
    }
    
    @Override
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build("profiles", newArrayList(
            PersonProfilesEnum.COMPANY_CLIENT.get(), PersonProfilesEnum.INDIVIDUAL_CLIENT.get())));
    	
    	return clientRepository.findAll(PersonPredicate.findByFilters(aggregator), pager, PersonPredicate.orderByName());
    }

    public BaseRepository<Person, Long> getRepository() {
        return this.clientRepository;
    }

}
