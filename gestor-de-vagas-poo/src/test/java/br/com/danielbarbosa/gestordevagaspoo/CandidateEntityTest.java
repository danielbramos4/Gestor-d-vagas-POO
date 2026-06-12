package br.com.danielbarbosa.gestordevagaspoo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.entities.CandidateEntity;

public class CandidateEntityTest {

    @Test
    void deveArmazenarNome() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setName("João");

        assertEquals("João", candidate.getName());
    }

    @Test
    void deveArmazenarUsername() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setUsername("joao123");

        assertEquals("joao123", candidate.getUsername());
    }

    @Test
    void deveArmazenarEmail() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setEmail("joao@email.com");

        assertEquals("joao@email.com", candidate.getEmail());
    }
}
