package com.example.spring_first_project.service;

import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.model.UserDemo;
import com.example.spring_first_project.repository.CompanyRepository;
import org.jetbrains.annotations.NotNull;
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

    public Company updateCompany(int id, @NotNull Company company) {
        company.setId(id);
        return companyRepository.save(company);
    }
    public void deleteCompanyById(int id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null && company.getUsers() != null) {
            for (UserDemo user : company.getUsers()) {
                user.setCompany(null); // Hủy liên kết giữa UserDemo và Company
            }
        }
        companyRepository.deleteById(id);
    }
    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }
}
