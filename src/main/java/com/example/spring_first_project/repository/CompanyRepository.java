package com.example.spring_first_project.repository;

import com.example.spring_first_project.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    List<Company> findAll();
}
