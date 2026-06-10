package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandateEntidy> findByUsernameOrEmail(String username, String email);
}
