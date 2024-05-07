package com.who.shuk.JobsMS.job.mapper;

import com.who.shuk.JobsMS.job.Job;
import com.who.shuk.JobsMS.job.dto.JobDTO;
import com.who.shuk.JobsMS.job.external.Company;
import com.who.shuk.JobsMS.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setDescription(job.getDescription());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setId(job.getId());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReview(reviews);
        return jobDTO;
    }
}
