package com.who.shuk.CompanyMS.company;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/companies")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies(){
        List<Company> aLlCompanies = companyService.getALlCompanies();
        return aLlCompanies;
    }
    @GetMapping("/{ID}")
    public Company getCompany(@PathVariable("ID") long id){
        Company companyById = companyService.getCompanyById(id);
        return companyById;
    }
    @PostMapping
    public Company addCompany(@RequestBody Company company){
        Company company1 = companyService.addCompany(company);
        return company1;
    }
    @DeleteMapping("/{ID}")
    public void deleteCompany(@PathVariable("ID") long id){
        companyService.deleteCompanyById(id);
    }
    @PutMapping
    public Company updateCompany(@RequestBody Company company){
        Company updatedCompany1 = companyService.update(company);

        return company;
    }

}
