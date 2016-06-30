package com.crm.infrastructure.repository.task;


import com.crm.infrastructure.entity.task.ChecklistTemplate;
import com.crm.infrastructure.entity.task.TaskTemplate;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChecklistTemplateRepository extends BaseRepository<ChecklistTemplate, Long> {

    @Query("SELECT ct FROM ChecklistTemplate AS ct WHERE ct.taskTemplate = :taskTemplate")
    List<ChecklistTemplate> findCheckListBy(@Param("taskTemplate") TaskTemplate taskTemplate);

}
