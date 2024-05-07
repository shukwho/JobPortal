package com.who.shuk.CompanyMS.company;

import java.util.List;

public interface CompanyService {
    List<Company> getALlCompanies();

    Company getCompanyById(long id);

    void deleteCompanyById(long id);

    Company addCompany(Company company);

    Company update(Company company);
}
