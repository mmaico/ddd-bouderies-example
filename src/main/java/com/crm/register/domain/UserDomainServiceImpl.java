package com.crm.register.domain;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.repository.UserRepository;
import com.crm.infrastructure.validators.CheckRule;
import com.crm.register.domain.contract.UserDomainService;
import com.crm.register.infrastructure.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crm.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.crm.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator validator;


    Map<String, CheckRule<User>> persistRules = new HashMap<>();

    {
        persistRules.put(description("user.already.existis.with.login"), (user) ->

            user.isNew() || user.getFields().contains("login")
                    ? userRepository.findByLogin(user.getLogin()).isPresent() && !userRepository.findByLogin(user.getLogin()).get().equals(user)
                    : Boolean.FALSE
        );

        persistRules.put(description("user.password.is.null"), (user) ->
                    !user.isNew() && user.getFields().contains("password")
                        ? isBlank(user.getPassword()) || isBlank(user.getPasswordConfirm())
                        : Boolean.FALSE
        );

        persistRules.put(description("user.password.not.equals"), (user) ->
                !user.isNew() && user.getFields().contains("password")
                    ? !user.getPassword().equals(user.getPasswordConfirm())
                    : Boolean.FALSE
        );

    }

    @Override
    public void checkBusinessRulesFor(User user) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(user))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
