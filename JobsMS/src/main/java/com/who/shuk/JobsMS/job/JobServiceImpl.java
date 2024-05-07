package com.who.shuk.JobsMS.job;

import com.who.shuk.JobsMS.job.dto.JobCompanyDTO;
import com.who.shuk.JobsMS.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    private List<Job> jobsList = new ArrayList<>();

    @Override
    public List<JobCompanyDTO> findAll() {
        /*List<JobCompanyDTO> jobCompanyList = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        for(Job job:jobList){  //try using stream & maps
            JobCompanyDTO jobCompanyDTO = convertToDTO(job);
            jobCompanyList.add(jobCompanyDTO);
        }*/
        List<Job> jobsList = jobRepository.findAll();
        List<JobCompanyDTO> jobCompanyList = jobsList.stream().map(this::convertToDTO).collect(Collectors.toList());
        return jobCompanyList;
    }
    private JobCompanyDTO convertToDTO(Job job){
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/"+job.getCompanyId(),
                Company.class);
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setJob(job);
        jobCompanyDTO.setCompany(company);
        return jobCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Optional<Job> findByID(long id) {
        return jobRepository.findById(id);
    }

    @Override
    public boolean delete(long id) {
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()){
            jobRepository.delete(job.get());
            return true;
        }

        return false;
    }

    @Override
    public Job update(Job job) {
        Optional<Job> job1 = jobRepository.findById(job.getId());
        if(job1.isPresent()){
            job1.get().setDescription(job.getDescription());
            job1.get().setLocation(job.getLocation());
            job1.get().setTitle(job.getTitle());
            job1.get().setMaxSalary(job.getMaxSalary());
            job1.get().setMinSalary(job.getMinSalary());
            jobRepository.save(job1.get());
        }
        return job1.get();
    }
}
