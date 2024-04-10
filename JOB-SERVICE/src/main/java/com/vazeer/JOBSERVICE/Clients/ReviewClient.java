package com.vazeer.JOBSERVICE.Clients;

import com.vazeer.JOBSERVICE.VO.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE", url = "http://localhost:8003")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Review> getReviewById(@RequestParam("companyId") Integer companyId);
}
