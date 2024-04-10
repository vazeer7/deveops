package com.vazeer.JOBSERVICE.Service;

import com.vazeer.JOBSERVICE.Clients.CompanyClient;
import com.vazeer.JOBSERVICE.Model.Job;
import com.vazeer.JOBSERVICE.Repository.JobRepository;
import com.vazeer.JOBSERVICE.VO.Company;
import com.vazeer.JOBSERVICE.VO.ResponseTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyClient companyClient;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<ResponseTemplateVo> getJobById(Integer jobId) {
        List<Job> jobs = jobRepository.findAll();
        List<ResponseTemplateVo> vos = new ArrayList<>();

        for (Job job: jobs){
            ResponseTemplateVo vo = new ResponseTemplateVo();
            Company company = companyClient.getCompany(job.getCompanyId());
            vo.setCompany(company);

            vos.add(vo);
        }
        return vos;
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
