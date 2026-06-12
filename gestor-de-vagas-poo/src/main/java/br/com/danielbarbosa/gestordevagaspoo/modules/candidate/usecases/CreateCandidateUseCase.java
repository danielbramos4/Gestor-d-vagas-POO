package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.usecases;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.UserFoundException;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.entities.CandidateEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.repositories.CandidateRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.shared.validators.SystemUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        SystemUserValidator.validate(candidateEntity);

        this.candidateRepository
                .findByUsernameOrEmail(
                        candidateEntity.getUsername(),
                        candidateEntity.getEmail()
                )
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidateEntity);
    }
}
