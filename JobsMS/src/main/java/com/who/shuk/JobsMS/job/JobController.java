package com.who.shuk.JobsMS.job;


/*import com.who.shuk.JobApplication.model.Job;
import com.who.shuk.JobApplication.service.JobService;*/
import com.who.shuk.JobsMS.job.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/jobs")
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

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        List<JobDTO> allJobs = jobService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allJobs);
    }
    @GetMapping("/{ID}")
    public ResponseEntity<JobDTO> findById(@PathVariable("ID") long id){
        JobDTO jobById = jobService.getJobById(id);
        if(jobById!=null)
            return new ResponseEntity<>(jobById, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }
    @DeleteMapping("/{ID}")
    public ResponseEntity deleteJob(@PathVariable("ID") long id){
        boolean isDeleted = jobService.delete(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job Does Not Exist");
        }

    }

    @PutMapping
    public Job updateJob(@RequestBody Job job){
        Job updatedjob = jobService.update(job);
        return updatedjob;
    }


}
