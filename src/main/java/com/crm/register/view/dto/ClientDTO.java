package com.crm.register.view.dto;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.Company;
import com.crm.infrastructure.entity.person.Individual;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.ReflectionsHelper;
import com.google.common.collect.Sets;

public class ClientDTO extends Person {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3425800714823005608L;
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

    public Person getClient() {
        if (COMPANY.equals(this.getType())) {
            ReflectionsHelper.copyProperties(this.company, this);
            return this.company;
        } else if (INDIVIDUAL.equals(this.getType())) {
            ReflectionsHelper.copyProperties(this.individual, this);
            return this.individual;
        } else {
            throw new ValidationException(Sets.newHashSet("client.not.have.type"));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientDTO{");
        sb.append(", type='").append(type).append('\'');
        sb.append(", individual=").append(individual);
        sb.append(", company=").append(company);
        sb.append('}');
        return sb.toString();
    }

}
