package com.crm.register.application;

import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.privider.Provider;
import com.crm.infrastructure.helpers.Filter;
import com.crm.infrastructure.helpers.FilterAggregator;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.repository.PersonRepository;
import com.crm.infrastructure.service.BaseModelServiceImpl;
import com.crm.register.application.contract.ProviderApplication;
import com.crm.register.domain.contract.ProviderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.crm.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static com.crm.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;
import static com.crm.infrastructure.repository.predicates.PersonPredicate.findByFilters;
import static com.crm.infrastructure.repository.predicates.PersonPredicate.orderByName;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class ProviderApplicationImpl extends BaseModelServiceImpl<Person> implements ProviderApplication {

    private PersonRepository providerRepository;
    private ProviderDomainService domainService;

    @Autowired
    public ProviderApplicationImpl(PersonRepository clientRepository, ProviderDomainService domainService) {
        this.providerRepository = clientRepository;
        this.domainService = domainService;
    }

    @Override
    public Provider register(Provider provider) {
        return super.save((Person)provider, domainService);
    }
    
    @Override
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build("profiles", newArrayList(INDIVIDUAL_PROVIDER.get(), COMPANY_PROVIDER.get())));
    	
    	return providerRepository.findAll(findByFilters(aggregator), pager, orderByName());
    }

    public BaseRepository<Person, Long> getRepository() {
        return this.providerRepository;
    }

}
