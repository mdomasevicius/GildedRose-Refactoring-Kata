package com.gildedrose.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.annotation.PostConstruct;

@Configuration
public class Config {

    @Autowired
    private RepositoryRestConfiguration configuration;

    @PostConstruct
    void configureDataRest() {
        configuration.setExposeRepositoryMethodsByDefault(false);
    }

}
