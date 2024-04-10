package com.vazeer.JOBSERVICE.Clients;

import com.vazeer.JOBSERVICE.VO.Company;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("COMPANY-SERVICE")
public interface CompanyClient {
    @GetMapping("/company/{companyId}")
    @LoadBalanced
    Company getCompany(@PathVariable("companyId") Integer id); //service cls rest template Return type and parameter passed in rest template should be a parameter here
}
