package com.who.shuk.JobsMS.job;

import com.who.shuk.JobsMS.job.dto.JobCompanyDTO;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<JobCompanyDTO> findAll();
    void createJob(Job job);

    Optional<Job> findByID(long id);

    boolean delete(long id);

    Job update(Job job);
}
