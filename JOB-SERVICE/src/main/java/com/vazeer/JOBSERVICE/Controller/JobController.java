package com.vazeer.JOBSERVICE.Controller;

import com.vazeer.JOBSERVICE.Model.Job;
import com.vazeer.JOBSERVICE.Service.JobService;
import com.vazeer.JOBSERVICE.VO.ResponseTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/list")
    public ResponseEntity<List<Job>> getAllJob(){
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/list/{jobId}")
    public ResponseEntity<List<ResponseTemplateVo>> getJobById(@PathVariable("jobId") Integer jobId){
        return new ResponseEntity<>(jobService.getJobById(jobId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.CREATED);
    }

    @PutMapping("/update/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable("jobId") Integer jobId, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(jobId, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully", HttpStatus.ACCEPTED);

        return new ResponseEntity<>("job details not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable("jobId") Integer jobId){
        boolean deleted = jobService.deleteJob(jobId);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Job details not found", HttpStatus.NOT_FOUND);
    }
}
