package com.example.spring_first_project.controller;

import com.example.spring_first_project.dto.CompanyApiDto;
import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.service.CompanyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestCompanyController {

    private CompanyServiceImpl companyService;

    public RestCompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> users() {
        List<Company> companies = companyService.getAllCompanies();
        System.out.println(companies);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable int id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping("/company")
    public ResponseEntity<Company> createCompany(@RequestBody CompanyApiDto company) {
        Company newCompany =  companyService.saveOrUpdate(company);
        return ResponseEntity.ok(newCompany);
    }

    @DeleteMapping("/company/{id}")
    public void deleteCompanyById(@PathVariable int id) {
        companyService.deleteCompanyById(id);
    }

    @PutMapping("/company/{id}")
    public Company updateCompanyById(@PathVariable int id, @RequestBody CompanyApiDto company) {
        return companyService.updateCompany(id, company);
    }
}
