package com.example.invoiceapp.controller;

import com.example.invoiceapp.model.Company;
import com.example.invoiceapp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public Company create(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
