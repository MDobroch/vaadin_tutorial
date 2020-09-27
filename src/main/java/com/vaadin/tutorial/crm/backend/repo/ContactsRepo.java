package com.vaadin.tutorial.crm.backend.repo;

import com.vaadin.tutorial.crm.backend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepo extends JpaRepository<Contact, Long> {

}
