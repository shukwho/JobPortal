package com.who.shuk.JobsMS.job.mapper;

import com.who.shuk.JobsMS.job.Job;
import com.who.shuk.JobsMS.job.dto.JobCompanyDTO;
import com.who.shuk.JobsMS.job.external.Company;

public class JobMapper {
    public static JobCompanyDTO mapToJobCompanyDTO(Job job, Company company){
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setDescription(job.getDescription());
        jobCompanyDTO.setTitle(job.getTitle());
        jobCompanyDTO.setId(job.getId());
        jobCompanyDTO.setLocation(job.getLocation());
        jobCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyDTO.setMinSalary(job.getMinSalary());
        jobCompanyDTO.setCompany(company);
        return jobCompanyDTO;
    }
}
