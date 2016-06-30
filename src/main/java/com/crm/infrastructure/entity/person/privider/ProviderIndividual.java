package com.crm.infrastructure.entity.person.privider;


public interface ProviderIndividual extends Provider {

    String getCpf();

    void setCpf(String cpf);

    String getLastname();

    void setLastname(String lastname);
}
