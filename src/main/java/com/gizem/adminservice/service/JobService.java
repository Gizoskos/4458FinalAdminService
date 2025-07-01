package com.gizem.adminservice.service;

import com.gizem.adminservice.dto.JobRequest;
import com.gizem.adminservice.entity.Job;

import java.util.List;

public interface JobService {
    Job create(JobRequest request);
    Job update(String id, JobRequest request);
    List<Job> findAll();
}
