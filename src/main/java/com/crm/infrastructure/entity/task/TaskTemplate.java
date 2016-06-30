package com.crm.infrastructure.entity.task;


import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.OperationRegion;
import com.crm.infrastructure.entity.saleable.SaleableUnit;
import com.google.common.collect.Lists;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="task_templates")
public class TaskTemplate extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "tasktemplate.title.is.empty")
    private String title;
    private String description;

    @OneToMany
    @JoinColumn(name="task_template_parent_id")
    @Cascade(CascadeType.DELETE)
    private List<TaskTemplate> templatesChilds;


    @Column(name = "task_template_parent_id", updatable =false, insertable = false)
    private Long parentId;

    @Column(name="quantity_days_to_finish_after_signed_contract")
    private Integer quantityDaysTofinishAfertSignedContract;

    @OneToMany
    @JoinColumn(name="task_template_id")
    private List<AppFile> files;

    @OneToMany(mappedBy = "taskTemplate")
    @Cascade(CascadeType.DELETE)
    private List<ChecklistTemplate> checklistTemplates;

    @OneToMany(mappedBy = "taskTemplate")
    @Cascade(CascadeType.DELETE)
    private List<TaskCostTemplate> tasksCostsTemplates;

    @ManyToOne
    @JoinColumn(name="saleable_unit_id")
    private SaleableUnit saleable;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "tasktemplate.region.not.informed")
    private OperationRegion region;

    @Transient
    private TaskTemplate parent;


    public void addChild(TaskTemplate taskTemplate) {
        if(this.templatesChilds == null) {
            this.templatesChilds = Lists.newArrayList();
        }
        this.templatesChilds.add(taskTemplate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskTemplate> getTemplatesChilds() {
        return templatesChilds;
    }

    public void setTemplatesChilds(List<TaskTemplate> templatesChilds) {
        this.templatesChilds = templatesChilds;
    }

    public Integer getQuantityDaysTofinishAfertSignedContract() {
        return quantityDaysTofinishAfertSignedContract;
    }

    public void setQuantityDaysTofinishAfertSignedContract(Integer quantityDaysTofinishAfertSignedContract) {
        this.quantityDaysTofinishAfertSignedContract = quantityDaysTofinishAfertSignedContract;
    }

    public List<AppFile> getFiles() {
        return files;
    }

    public void setFiles(List<AppFile> files) {
        this.files = files;
    }

    public List<ChecklistTemplate> getChecklistTemplates() {
        return checklistTemplates;
    }

    public void setChecklistTemplates(List<ChecklistTemplate> checklistTemplates) {
        this.checklistTemplates = checklistTemplates;
    }

    public List<TaskCostTemplate> getTasksCostsTemplates() {
        return tasksCostsTemplates;
    }

    public void setTasksCostsTemplates(List<TaskCostTemplate> tasksCostsTemplates) {
        this.tasksCostsTemplates = tasksCostsTemplates;
    }

    public SaleableUnit getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnit saleable) {
        this.saleable = saleable;
    }

    public OperationRegion getRegion() {
        return region;
    }

    public void setRegion(OperationRegion region) {
        this.region = region;
    }


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskTemplate getParent() {
        return parent;
    }

    public void setParent(TaskTemplate parent) {
        this.parent = parent;
    }

    public Boolean hasValidParent() {
        return parent != null && !parent.isNew();

    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
