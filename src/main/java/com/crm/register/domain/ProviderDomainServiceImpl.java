package com.crm.register.domain;

import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.register.domain.contract.AddressDomainService;
import com.crm.register.domain.contract.ProviderDomainService;
import com.crm.infrastructure.entity.enums.PersonProfilesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class ProviderDomainServiceImpl implements ProviderDomainService {

    @Autowired
    private AddressDomainService service;

	@Override
	public void checkBusinessRulesFor(Person provider) {
		
		if(!PersonProfilesEnum.INDIVIDUAL_PROVIDER.get().equals(provider.getProfile()) &&
				!PersonProfilesEnum.COMPANY_PROVIDER.get().equals(provider.getProfile())) {
			throw new ValidationException(newHashSet("provider.without.profile"));
		}
        service.prepareToSave(provider.getAddresses());
	}

	
}
