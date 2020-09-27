package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.repo.CompanyRepo;
import com.vaadin.tutorial.crm.backend.repo.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactsService {
    private static final Logger LOGGER = Logger.getLogger(ContactsService.class.getName());

    @Autowired
    private ContactsRepo contactsRepo;

    @Autowired
    private CompanyRepo companyRepo;


    public List<Contact> findAll() {
        return contactsRepo.findAll();
    }

    public long count() {
        return contactsRepo.count();
    }

    public void delete(Contact contact) {
        contactsRepo.delete(contact);
    }

    public void save(Contact contact) {
        if (contact == null) {
            LOGGER.log(Level.SEVERE, "Contacts is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactsRepo.save(contact);
    }

    @PostConstruct
    public void populateTestData() {
        if (contactsRepo.count() == 0) {
            companyRepo.saveAll(
                    Stream.of("Path-Way Electronics", "E-Tech Management", "Path-E-Tech Management")
                            .map(Company::new)
                            .collect(Collectors.toList()));
        }

        if (contactsRepo.count() == 0) {
            Random r = new Random(0);
            List<Company> companies = companyRepo.findAll();
            contactsRepo.saveAll(
                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                            "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                            "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                            "Jaydan Jackson", "Bernard Nilsen")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Contact contact = new Contact();
                                contact.setFirstName(split[0]);
                                contact.setLastName(split[1]);
                                contact.setCompany(companies.get(r.nextInt(companies.size())));
                                contact.setStatus(Contact.Status.values()[r.nextInt(Contact.Status.values().length)]);
                                String email = (contact.getFirstName() + "." + contact.getLastName() + "@" + contact.getCompany().getName().replaceAll("[\\s-]", "") + ".com").toLowerCase();
                                contact.setEmail(email);
                                return contact;
                            }).collect(Collectors.toList()));
        }
    }

}
