package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.service.ContactsService;

@Route("")
public class MainView extends VerticalLayout {

    Grid<Contact> grid = new Grid<>(Contact.class);

    private ContactsService contactsService;

    public MainView(ContactsService contactsService) {
        this.contactsService = contactsService;
        addClassName("list-view"); //style
        setSizeFull(); //full window size

        configureGrid();

        add(grid);

        updateList();
    }

    private void updateList() {
        grid.setItems(contactsService.findAll());
    }

    private void configureGrid() {
        addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");

        grid.addColumn( contact -> {
           Company company = contact.getCompany();
           return company == null ? "-" : company.getName();

        }).setHeader("Company");


        grid.getColumns().forEach( contactColumn -> {contactColumn.setAutoWidth(true);});

    }


}
