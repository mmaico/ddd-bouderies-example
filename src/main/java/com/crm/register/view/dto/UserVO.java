package com.crm.register.view.dto;

import java.io.IOException;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.helpers.ReflectionsHelper;
import org.springframework.web.multipart.MultipartFile;

public class UserVO extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1224149976031181831L;
	
	private MultipartFile avatarFile;

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}
	
	public User getUser() {
		User user = new User();
		ReflectionsHelper.copyProperties(user, this);
		try {
			user.setAvatar(avatarFile != null ? avatarFile.getBytes(): null);
		} catch (IOException e) {
			
		}
		return user;
		
	}
}
