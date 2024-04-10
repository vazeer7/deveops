package com.vazeer.JOBSERVICE.Clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("REVIEW-SERVICE")
public interface ReviewClient {


}
