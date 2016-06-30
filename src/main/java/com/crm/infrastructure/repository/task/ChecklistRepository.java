package com.crm.infrastructure.repository.task;


import com.crm.infrastructure.entity.task.Checklist;
import com.crm.infrastructure.entity.task.Task;
import com.crm.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChecklistRepository extends BaseRepository<Checklist, Long> {

    @Query("SELECT c FROM Checklist AS c WHERE c.task = :task")
    List<Checklist> findCheckListBy(@Param("task") Task task);

    @Query("SELECT c FROM Checklist AS c WHERE c.id = :id")
    Optional<Checklist> getOne(@Param("id")Long id);

}
