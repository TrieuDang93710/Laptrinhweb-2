package com.example.spring_first_project.repository;

import com.example.spring_first_project.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    List<Company> findAll();
}
