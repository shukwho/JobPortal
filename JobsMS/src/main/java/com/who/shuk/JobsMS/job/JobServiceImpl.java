package com.who.shuk.JobsMS.job;

import com.who.shuk.JobsMS.job.clients.CompanyClient;
import com.who.shuk.JobsMS.job.clients.ReviewClient;
import com.who.shuk.JobsMS.job.dto.JobDTO;
import com.who.shuk.JobsMS.job.external.Company;
import com.who.shuk.JobsMS.job.external.Review;
import com.who.shuk.JobsMS.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;
    private List<Job> jobsList = new ArrayList<>();

    @Override
    //@CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    //@Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        /*List<JobCompanyDTO> jobCompanyList = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        for(Job job:jobList){  //try using stream & maps
            JobCompanyDTO jobCompanyDTO = convertToDTO(job);
            jobCompanyList.add(jobCompanyDTO);
        }*/
        List<Job> jobsList = jobRepository.findAll();
        List<JobDTO> jobCompanyList = jobsList.stream().map(this::convertToDTO).collect(Collectors.toList());
        return jobCompanyList;
    }
   
    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(long id) {
        Job job =jobRepository.findById(id).orElse(null);
        JobDTO jobDTO = convertToDTO(job);
        return jobDTO;
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
    private JobDTO convertToDTO(Job job){
        //RestTemplate restTemplate = new RestTemplate();

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        /*Above changes to use openfeign
        Company company = restTemplate.getForObject("http://CompanyMS:8081/companies/"+job.getCompanyId(),
                Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://ReviewMS:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                });
                List<Review> reviews = reviewResponse.getBody();*/


        JobDTO jobDTO = JobMapper.mapToJobDTO(job, company,reviews);
        /*
        Ceated JobMapper class to Map to DTO class
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setJob(job);
        jobCompanyDTO.setCompany(company);*/
        return jobDTO;
    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

}
