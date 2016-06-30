package com.crm.infrastructure.entity.person.client;


public interface ClientIndividual extends Client {

    String getCpf();

    void setCpf(String cpf);

    String getLastname();

    void setLastname(String lastname);
}
