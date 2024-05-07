package com.who.shuk.JobsMS.job;

import com.who.shuk.JobsMS.job.dto.JobCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobCompanyDTO> findAll();
    void createJob(Job job);

    JobCompanyDTO getJobById(long id);

    boolean delete(long id);

    Job update(Job job);
}
