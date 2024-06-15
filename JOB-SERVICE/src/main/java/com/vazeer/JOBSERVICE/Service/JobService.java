package com.vazeer.JOBSERVICE.Service;

import com.vazeer.JOBSERVICE.Clients.CompanyClient;
import com.vazeer.JOBSERVICE.Clients.ReviewClient;
import com.vazeer.JOBSERVICE.Mapper.JobMapper;
import com.vazeer.JOBSERVICE.Model.Job;
import com.vazeer.JOBSERVICE.Repository.JobRepository;
import com.vazeer.JOBSERVICE.VO.Company;
import com.vazeer.JOBSERVICE.VO.ResponseTemplateVo;
import com.vazeer.JOBSERVICE.VO.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JobService {

    int attempt = 0;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker")
    public List<ResponseTemplateVo> getAllJobs() {
        System.out.println("Attempt"+ ++attempt);
        List<Job> jobList = jobRepository.findAll();

        return jobList.stream().map(this::convertToDTO).
                collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    public Job getJobById(Integer jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    private ResponseTemplateVo convertToDTO(Job job){
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> review = reviewClient.getReviewById(job.getCompanyId());

        ResponseTemplateVo vo = JobMapper.mapResponseTemplateVo(job, company, review);

        return vo;
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    public boolean updateJob(Integer id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setJobDesignation(updatedJob.getJobDesignation());
            job.setJobLocation(updatedJob.getJobLocation());
            job.setJobName(updatedJob.getJobName());
            job.setJobSalary(updatedJob.getJobSalary());

            jobRepository.save(job);
            return true;
        }
        return false;
    }

    public boolean deleteJob(Integer jobId) {
        try{
            jobRepository.deleteById(jobId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
