package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.UseCases;


import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.JobEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobCompanyUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
