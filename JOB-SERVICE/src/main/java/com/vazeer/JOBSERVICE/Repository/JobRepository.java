package com.vazeer.JOBSERVICE.Repository;

import com.vazeer.JOBSERVICE.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}
