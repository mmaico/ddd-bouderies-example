package com.crm.register.domain;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.register.domain.contract.ClientDomainService;
import com.crm.register.infrastructure.validators.ClientValidator;
import com.crm.infrastructure.helpers.RuleExpressionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_CLIENT;
import static com.crm.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_CLIENT;
import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;


@Service
public class ClientDomainServiceImpl implements ClientDomainService {


    @Autowired
    private ClientValidator validator;


    Map<String, CheckRule<Person>> persistRules = new HashMap<>();

    {
        persistRules.put(RuleExpressionHelper.description("client.without.profile"), (client) ->
                    !INDIVIDUAL_CLIENT.get().equals(client.getProfile()) && !COMPANY_CLIENT.get().equals(client.getProfile()));

        //persistRules.put(description("client.verify.base.validate"), (task) -> hasContraintViolated(task, validator));
    }


    @Override
    public void checkBusinessRulesFor(Person client) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(client))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }



}
