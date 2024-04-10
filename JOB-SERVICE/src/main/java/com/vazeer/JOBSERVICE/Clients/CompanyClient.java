package com.vazeer.JOBSERVICE.Clients;

import com.vazeer.JOBSERVICE.VO.Company;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE", url = "http://localhost:8002")
public interface CompanyClient {
    @GetMapping("/company/{companyId}")
    Company getCompany(@PathVariable("companyId") Integer id); //service cls rest template Return type and parameter passed in rest template should be a parameter here
}
