package com.crm.auditing.infrastructure.configuration;

import com.crm.auditing.infrastructure.GsonExclusionStrategy;
import com.crm.auditing.infrastructure.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {


    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setExclusionStrategies(GsonExclusionStrategy.create())
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .create();
    }
}
