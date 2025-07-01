package com.gizem.adminservice.controller;

import com.gizem.adminservice.dto.JobRequest;
import com.gizem.adminservice.entity.Job;
import com.gizem.adminservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/jobs")
public class JobController {

    @Autowired
    private JobService service;

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody JobRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable String id, @RequestBody JobRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
