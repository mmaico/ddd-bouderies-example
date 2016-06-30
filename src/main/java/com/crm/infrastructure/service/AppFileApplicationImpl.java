package com.crm.infrastructure.service;

import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.HandlerErrors;
import com.crm.infrastructure.repository.AppFileRepository;
import com.crm.infrastructure.repository.BaseRepository;
import com.crm.infrastructure.validators.AppFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppFileApplicationImpl extends BaseModelServiceImpl<AppFile> implements AppFileApplication {

    @Autowired
    private AppFileRepository repository;

    @Autowired
    private FilePersistHelper filePersist;

    @Autowired
    private AppFileValidator validator;

    public AppFile save(Identifiable identifiable, AppFile appFile) {

        HandlerErrors.hasErrors(validator.hasFileAndRequiredInfos(appFile))
                .throwing(ValidationException.class);

        AppFile appFileSaved = super.save(appFile);
        filePersist.saveFile(identifiable, appFileSaved);
        return appFileSaved;
    }

    @Override
    public BaseRepository<AppFile, Long> getRepository() {
        return repository;
    }
}
