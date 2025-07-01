package com.gizem.adminservice.service;

import com.gizem.adminservice.dto.JobRequest;
import com.gizem.adminservice.entity.Job;
import com.gizem.adminservice.repository.JobRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Override
    public Job create(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.title);
        job.setCompany(request.company);
        job.setCity(request.city);
        job.setCountry(request.country);
        job.setDepartment(request.department);
        job.setDescription(request.description);
        job.setWorkingType(request.workingType);
        job.setLastUpdated(LocalDateTime.now());
        job.setApplicationCount(0);

        // Job nesnesi MongoDB'ye kaydediliyor
        Job saved = repo.save(job);

        // Kaydedilen ilan bilgisi RabbitMQ'ya publish ediliyor
        // exchange: mesajın yönlendirileceği exchange adı
        // routingKey: mesajın ilgili kuyruğa gitmesi için kullanılan anahtar
        // saved: gönderilecek içerik (Job nesnesi JSON'a dönüştürülür)
        rabbitTemplate.convertAndSend(exchange, routingKey, saved);
        return saved;
    }

    @Override
    public Job update(String id, JobRequest request) {
        Job job = repo.findById(id).orElseThrow();
        job.setTitle(request.title);
        job.setCompany(request.company);
        job.setCity(request.city);
        job.setCountry(request.country);
        job.setDepartment(request.department);
        job.setDescription(request.description);
        job.setWorkingType(request.workingType);
        job.setLastUpdated(LocalDateTime.now());
        return repo.save(job);
    }

    @Override
    public List<Job> findAll() {
        return repo.findAll();
    }
}
