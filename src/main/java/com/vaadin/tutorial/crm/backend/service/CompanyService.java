package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CompanyService {
    private static Logger LOGGER = Logger.getLogger(CompanyService.class.getName());


    @Autowired
    private CompanyRepo companyRepo;


    public List<Company> findAll(){
        return companyRepo.findAll();
    }

    public long count(){
        return companyRepo.count();
    }

    public void detele(Company company){
         companyRepo.delete(company);
    }

    public void save(Company company){
        if(company == null){
            LOGGER.log(Level.SEVERE, "You cannot add empty company");
            return;
        }
       companyRepo.save(company);
    }

}
