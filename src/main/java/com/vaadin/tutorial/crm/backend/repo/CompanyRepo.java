package com.vaadin.tutorial.crm.backend.repo;

import com.vaadin.tutorial.crm.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {
}
