package com.pluralsight.conferencedemo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration
{
/*
    //When this method returns the DataSource object, Spring looks for it and sees if one exists in the Spring context.
    //If one does, it will replace it with the one that it found.
    //@Bean is used so Spring knows that this method is a Spring Bean.

    @Bean
    public DataSource dataSource()
    {
        DataSourceBuilder builder = DataSourceBuilder.create();


        //Use Environment variables to keep these fields (username and password) hidden
        //Names for environment variables: @Value("${DB_URL}"), @Value("${DB_Username}"), @Value("${DB_Pw}")
        //Assign them to private String(s) and put them into the methods below.

        builder.url("jdbc:postgresql://localhost:5432/conference_app");
        builder.username("postgres");
        builder.password("m,./M<>?");
        System.out.println("My custom datasource bean has been initialized and set.");

        return builder.build();
    }*/
}