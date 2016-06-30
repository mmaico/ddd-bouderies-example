package com.crm.infrastructure.security.authentication;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.security.UserSecurityInfoService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserSecurityInfoService service;



    @Override
    protected UserDetails retrieveUser(String login, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();

        if (isBlank(password)) {
            throw new BadCredentialsException("security.invalid.password");
        }

        Optional<User> userFound = service.getUser(login, password);

        if (!userFound.isPresent()) {
            throw new BadCredentialsException("security.user.not.found");
        }

        LoggedUser loggedBuilt = LoggedUserBuilder.createLoggedUser(login, userFound.get(), Sets.newHashSet()).build();

        authentication.setDetails(loggedBuilt);
        return new org.springframework.security.core.userdetails.User(login, password, true, true, true, true, loggedBuilt.getAuthorities());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}
}
