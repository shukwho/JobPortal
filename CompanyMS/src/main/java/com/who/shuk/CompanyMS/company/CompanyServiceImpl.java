package com.who.shuk.CompanyMS.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

   /* @Autowired
    private JobRepository jobRepository;*/
    @Override
    public List<Company> getALlCompanies() {
        List<Company> allCompanies = companyRepository.findAll();
        return allCompanies;
    }

    @Override
    public Company getCompanyById(long id) {
        Optional<Company> byId = companyRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public void deleteCompanyById(long id) {
        companyRepository.deleteById(id);

    }

    @Override
    public Company addCompany(Company company) {
        companyRepository.save(company);
       // jobRepository.save(company.getJobs().get(0));
        return company;
    }

    public Company update(Company company) {
        Optional<Company> company1 = companyRepository.findById(company.getId());
        if(company1.isPresent()){
            company1.get().setDescription(company.getDescription());
            company1.get().setName(company.getName());
        }
        return company1.get();
    }
}
