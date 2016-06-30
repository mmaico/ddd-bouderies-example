package com.crm.register.view.dto;

import java.io.IOException;

import com.crm.infrastructure.helpers.MultipartFileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crm.infrastructure.exceptions.ValidationException;

import com.google.common.collect.Sets;

public class AvatarVO {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public byte[] get() {
		try {
			return MultipartFileUtils.safe(file).getBytes();
		} catch (IOException e) {
			throw new ValidationException(Sets.newHashSet("file.invalid"));
		}
	}
	
}
