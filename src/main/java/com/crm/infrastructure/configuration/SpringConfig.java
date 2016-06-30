package com.crm.infrastructure.configuration;


import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableSpringConfigured
@EnableScheduling
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"com.crm"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.crm")
public class SpringConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {


    /**
     * TODO: colocar resource location do properties.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/view-resource/**")
                   .addResourceLocations("file:/opt/templates/");
    }


}
