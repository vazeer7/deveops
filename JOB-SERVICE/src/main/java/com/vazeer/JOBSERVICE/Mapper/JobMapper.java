package com.vazeer.JOBSERVICE.Mapper;

import com.vazeer.JOBSERVICE.Model.Job;
import com.vazeer.JOBSERVICE.VO.Company;
import com.vazeer.JOBSERVICE.VO.ResponseTemplateVo;

public class JobMapper {

    public static ResponseTemplateVo mapResponseTemplateVo(Job job, Company company){

        ResponseTemplateVo responseTemplateVo = new ResponseTemplateVo();
        responseTemplateVo.setJobId(job.getCompanyId());
        responseTemplateVo.setJobDesignation(job.getJobDesignation());
        responseTemplateVo.setJobLocation(job.getJobLocation());
        responseTemplateVo.setJobName(job.getJobName());
        responseTemplateVo.setJobSalary(job.getJobSalary());
        responseTemplateVo.setCompany(company);

        return responseTemplateVo;

    }
}
