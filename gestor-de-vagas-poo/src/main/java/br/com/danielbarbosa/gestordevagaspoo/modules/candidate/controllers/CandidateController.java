package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.entities.CandidateEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        var result = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok().body(result);
    }
}
