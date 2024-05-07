package com.who.shuk.JobsMS.job.dto;

import com.who.shuk.JobsMS.job.Job;
import com.who.shuk.JobsMS.job.external.Company;

public class JobCompanyDTO {
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
