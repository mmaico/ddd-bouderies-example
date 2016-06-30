package com.crm.infrastructure.entity.builders;


import com.crm.infrastructure.entity.person.Person;
import com.crm.infrastructure.entity.person.privider.Provider;

public class ProviderBuilder extends AbstractBuilder<Provider>  {

	public ProviderBuilder() {
		this.entity = new Person();
	}

	public ProviderBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
    public ProviderBuilder withId(Long id) {
        this.entity.setId(id);
        return this;
    }
	public static ProviderBuilder createProvider(Long id) {
		return new ProviderBuilder(id);
	}

	public static ProviderBuilder createProvider() {
		return new ProviderBuilder();
	}
}
