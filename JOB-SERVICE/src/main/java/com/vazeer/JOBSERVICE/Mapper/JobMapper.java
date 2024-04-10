package com.vazeer.JOBSERVICE.Mapper;

import com.vazeer.JOBSERVICE.Model.Job;
import com.vazeer.JOBSERVICE.VO.Company;
import com.vazeer.JOBSERVICE.VO.ResponseTemplateVo;
import com.vazeer.JOBSERVICE.VO.Review;

import java.util.List;

public class JobMapper {

    public static ResponseTemplateVo mapResponseTemplateVo(Job job, Company company, List<Review> review){

        ResponseTemplateVo responseTemplateVo = new ResponseTemplateVo();
        responseTemplateVo.setJobId(job.getCompanyId());
        responseTemplateVo.setJobDesignation(job.getJobDesignation());
        responseTemplateVo.setJobLocation(job.getJobLocation());
        responseTemplateVo.setJobName(job.getJobName());
        responseTemplateVo.setJobSalary(job.getJobSalary());
        responseTemplateVo.setCompany(company);
        responseTemplateVo.setReview(review);

        return responseTemplateVo;

    }
}
