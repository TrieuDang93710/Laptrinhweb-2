package com.example.spring_first_project.service;

import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public void saveOrUpdate(Company company) {
        companyRepository.save(company);
    }
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company updateCompany(int id, Company company) {
        company.setId(id);
        return companyRepository.save(company);
    }
    public void deleteCompanyById(int id) {
        companyRepository.deleteById(id);
    }
    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }
}
