package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.Repositories;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
