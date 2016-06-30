package com.crm.infrastructure.service;


import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.Identifiable;

public interface AppFileApplication extends ModelService<AppFile> {

	AppFile save(Identifiable identifiable, AppFile appFile);

}
