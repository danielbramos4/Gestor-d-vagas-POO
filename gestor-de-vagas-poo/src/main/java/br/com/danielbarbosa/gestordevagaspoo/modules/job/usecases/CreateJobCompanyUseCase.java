package br.com.danielbarbosa.gestordevagaspoo.modules.job.usecases;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.BusinessValidationException;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.entities.JobEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobCompanyUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        if (isBlank(jobEntity.getDescription())) {
            throw new BusinessValidationException("Informe a descricao da vaga.");
        }

        if (isBlank(jobEntity.getLevel())) {
            throw new BusinessValidationException("Informe o nivel da vaga.");
        }

        if (jobEntity.getCompanyId() == null) {
            throw new BusinessValidationException("Selecione uma empresa para a vaga.");
        }

        return this.jobRepository.save(jobEntity);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
