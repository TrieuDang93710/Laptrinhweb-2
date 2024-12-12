package com.example.spring_first_project.controller.restController;

import com.example.spring_first_project.dto.ApiResponse;
import com.example.spring_first_project.dto.CompanyApiDto;
import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.service.CompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<ApiResponse<Company>> findCompanyById(@PathVariable int id) {
        try{
            Company company = companyService.getCompanyById(id);
            if (company == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Company not found", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Company found", company));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    @PostMapping("/company")
    public ResponseEntity<ApiResponse<Company>> createCompany(@RequestBody CompanyApiDto company) {
        try{
            Company newCompany =  companyService.saveOrUpdate(company);
            if (newCompany == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Company not found", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Company just creation", newCompany));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }

    }

    @DeleteMapping("/company/{id}")
    public void deleteCompanyById(@PathVariable int id) {
        companyService.deleteCompanyById(id);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<ApiResponse<Company>> updateCompanyById(@PathVariable int id, @RequestBody CompanyApiDto company) {
        try{
            Company updateCompany =  companyService.updateCompany(id, company);
            if (updateCompany == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Company not found", null));
            }
            return ResponseEntity.ok(new ApiResponse<>(200, "Company just updated", updateCompany));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(403, "Access Denied", null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }
}
