package br.com.danielbarbosa.gestordevagaspoo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.CandidateEntity;

class CreateCandidateUseCaseTest {

    @Test
    void deveCriarCandidato() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setName("João");
        candidate.setUsername("joao");
        candidate.setEmail("joao@email.com");

        assertEquals("João", candidate.getName());
        assertEquals("joao", candidate.getUsername());
        assertEquals("joao@email.com", candidate.getEmail());
    }
    @Test
    void deveVerificarNome() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setName("Maria");

        assertEquals("Maria", candidate.getName());
    }
    @Test
    void deveVerificarUsername() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setUsername("maria123");

        assertEquals("maria123", candidate.getUsername());
    }
    @Test
    void deveVerificarEmail() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setEmail("maria@email.com");

        assertEquals("maria@email.com", candidate.getEmail());
    }
    @Test
    void naoDeveAceitarEmailDiferente() {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setEmail("teste@email.com");

        assertNotEquals("outro@email.com", candidate.getEmail());
    }
}