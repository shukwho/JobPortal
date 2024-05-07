package com.who.shuk.JobsMS.job;


/*import com.who.shuk.JobApplication.model.Job;
import com.who.shuk.JobApplication.service.JobService;*/
import com.who.shuk.JobsMS.job.dto.JobCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    /*
    Loosely coupled
    public JobController(JobService jobService){
        this.jobService=jobService;
    }
    */

    @GetMapping("/jobs")
    public ResponseEntity<List<JobCompanyDTO>> findAll(){
        List<JobCompanyDTO> allJobs = jobService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allJobs);
    }
    @GetMapping("/jobs/{ID}")
    public ResponseEntity<JobCompanyDTO> findById(@PathVariable("ID") long id){
        JobCompanyDTO jobById = jobService.getJobById(id);
        if(jobById!=null)
            return new ResponseEntity<>(jobById, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }
    @DeleteMapping("/jobs/{ID}")
    public ResponseEntity deleteJob(@PathVariable("ID") long id){
        boolean isDeleted = jobService.delete(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job Does Not Exist");
        }

    }

    @PutMapping("/jobs")
    public Job updateJob(@RequestBody Job job){
        Job updatedjob = jobService.update(job);
        return updatedjob;
    }


}
