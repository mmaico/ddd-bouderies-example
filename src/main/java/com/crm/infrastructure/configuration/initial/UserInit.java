package com.crm.infrastructure.configuration.initial;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.builders.UserBuilder;
import com.crm.infrastructure.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * Cria um usuario admin caso nao tenha nenhum usuario no banco.
 */
@Component
public class UserInit implements InitialProcess {

	private static final String LOGIN = "admin";
	private static final String PASSWD = "admin";
	private static final String NAME = "Administrador do sistema";
	
	@Autowired
	private UserRepository userRepository;


	@Override
	@PostConstruct
	public void run() {

		long count = userRepository.count();
		if (count < 1) {
			byte[] byteArray = getAvatar();

            User adminUser = UserBuilder.createUser(1l)
                    .withLogin(LOGIN).withPassword(PASSWD)
                    .withName(NAME)
                    .withAvatar(byteArray).build();

            userRepository.save(adminUser);
        }
	}

	private byte[] getAvatar() {
		InputStream result = UserInit.class.getResourceAsStream("/admin-icon.png");
		byte[] byteArray = null;
		try {
			byteArray = IOUtils.toByteArray(result);
		} catch (IOException e) {}
		return byteArray;
	}

	
}
