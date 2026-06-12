package br.com.danielbarbosa.gestordevagaspoo.modules.job.controllers;

import br.com.danielbarbosa.gestordevagaspoo.modules.job.usecases.CreateJobCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielbarbosa.gestordevagaspoo.modules.job.entities.JobEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobCompanyUseCase createJobCompanyUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {
        return this.createJobCompanyUseCase.execute(jobEntity);
    }
}
