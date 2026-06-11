package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.Repositories;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

}