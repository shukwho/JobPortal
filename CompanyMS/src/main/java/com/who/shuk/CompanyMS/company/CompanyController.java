package com.who.shuk.CompanyMS.company;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getAllCompanies(){
        List<Company> aLlCompanies = companyService.getALlCompanies();
        return aLlCompanies;
    }
    @GetMapping("/companies/{ID}")
    public Company getCompany(@PathVariable("ID") long id){
        Company companyById = companyService.getCompanyById(id);
        return companyById;
    }
    @PostMapping("/companies")
    public Company addCompany(@RequestBody Company company){
        Company company1 = companyService.addCompany(company);
        return company1;
    }
    @DeleteMapping("/companies/{ID}")
    public void deleteCompany(@PathVariable("ID") long id){
        companyService.deleteCompanyById(id);
    }
    @PutMapping("/companies")
    public Company updateCompany(@RequestBody Company company){
        Company updatedCompany1 = companyService.update(company);

        return company;
    }

}
