package com.olliekrk.contactsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.olliekrk.contactsapp.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.olliekrk.contactsapp.entities")
public class ContactsAppApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ContactsAppApplication.class, args);
    }

}
