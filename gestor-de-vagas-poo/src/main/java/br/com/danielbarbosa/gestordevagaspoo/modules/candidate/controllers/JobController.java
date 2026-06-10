package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.UseCases.CreateJobCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.JobEntity;
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
