package com.crm.infrastructure.configuration.initial;

import com.crm.infrastructure.entity.enums.PersonProfilesEnum;
import com.crm.infrastructure.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Cria os profiles que serao usados no cadastro de clientes e fornecedores.
 */
@Component
public class PersonProfilesInit implements InitialProcess {

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	@PostConstruct
	public void run() {

		long count = profileRepository.count();
		if (count < 1) {
			profileRepository.save(PersonProfilesEnum.getAll());
		}
	}

	
}
