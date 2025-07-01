package com.gizem.adminservice.repository;

import com.gizem.adminservice.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String> {
}
