package com.example.spring_first_project.controller;

import com.example.spring_first_project.model.Company;
import com.example.spring_first_project.service.CompanyService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public String trangChiTiet(@ModelAttribute("userName") String userName, Model model) {
        List<Company> list = companyService.getAllCompanies();
        model.addAttribute("company", list);
        return "company";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    @PostMapping("addCompany")
    public String addCompany(@ModelAttribute("company") Company company) {
        companyService.saveOrUpdate(company);
        return "company";
    }

    // Hiển thị form cập nhật
    @GetMapping("/updateCompany/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "updateCompany";
    }

    // Thực hiện cập nhật
    @PostMapping("/updateCompany/{id}")
    public String updateCompany(@PathVariable int id, @ModelAttribute("company") Company company) {
//        company.setId(id); // Đảm bảo rằng ID được cập nhật
//        companyService.saveOrUpdate(company);
        companyService.updateCompany(id, company);
        return "redirect:/company";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompanyById(id);
        return "redirect:/company";
    }
}
