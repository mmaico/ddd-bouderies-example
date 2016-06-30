package com.crm.infrastructure.service;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.files.FileSystemHelper;
import com.crm.infrastructure.repository.AppFileRepository;
import com.crm.infrastructure.validators.AppFileValidator;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.crm.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Service
public class FilePersistHelper {

	
	private @Autowired
	AppFileRepository repository;
	
	private @Autowired
	FileSystemHelper fileSystemHelper;

	private @Autowired
    AppFileValidator appFileValidator;
	
	public byte[] getFile(Identifiable entity, AppFile appFile) {
		
		if (appFile == null || appFile.isNew() || entity == null || entity.isNew()) {
			throw new IllegalArgumentException("invalid.arguments.entity.or.appfile.cannot.be.null");
		}
		
		AppFile appFileLoaded = repository.findOne(appFile.getId());
		
		byte[] file = this.fileSystemHelper.getFile(entity, appFileLoaded);
		
		return file;
	}

	public void saveFile(Identifiable entity, AppFile appFile) {

		this.appFileValidator.hasFileAndRequiredInfos(appFile);

		if (entity == null || entity.isNew()) {
			throw new ValidationException(Sets.newHashSet("save.file.entity.not.have.id"));
		}
		
		String basePath = this.fileSystemHelper.getBasePath(entity);
		this.fileSystemHelper.mkdirs(basePath);
		
		String fullPathFile = this.fileSystemHelper.getPathFile(entity, appFile);
		
		this.fileSystemHelper.writeFile(fullPathFile, appFile.getFile());
	}

	public void saveFile(Identifiable entity, List<AppFile> appFiles) {

        if (isEmptySafe(appFiles)) return;

        appFiles.stream().forEach(e -> saveFile(entity, e));
    }

}
