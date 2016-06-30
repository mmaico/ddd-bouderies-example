package com.crm.register.view.dto;


import com.crm.infrastructure.entity.person.Company;
import com.crm.infrastructure.entity.person.Individual;
import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.helpers.ReflectionsHelper;

public class ProviderDTO extends Person {

    private static final String COMPANY = "company";
    private static final String INDIVIDUAL = "individual";

    private String type;

    private Individual individual = new Individual();

    private Company company = new Company();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getProvider() {
        if (COMPANY.equals(this.getType())) {
            ReflectionsHelper.copyProperties(this.company, this);
            return this.company;
        } else if (INDIVIDUAL.equals(this.getType())) {
            ReflectionsHelper.copyProperties(this.individual, this);
            return this.individual;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProviderDTO{");
        sb.append(", type='").append(type).append('\'');
        sb.append(", individual=").append(individual);
        sb.append(", company=").append(company);
        sb.append('}');
        return sb.toString();
    }

}
