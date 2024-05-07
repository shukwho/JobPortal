package com.who.shuk.JobsMS.job;

import com.who.shuk.JobsMS.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById(long id);

    boolean delete(long id);

    Job update(Job job);
}
