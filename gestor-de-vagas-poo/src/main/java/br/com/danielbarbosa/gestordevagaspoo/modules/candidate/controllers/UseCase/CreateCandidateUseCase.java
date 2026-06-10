package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.UseCase;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.UserFoundException;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.CandidateEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.CandidateRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        return this.candidateRepository.save(candidateEntity);
    }
}
