package br.com.danielbarbosa.gestordevagaspoo.modules.job.repositories;

import br.com.danielbarbosa.gestordevagaspoo.modules.job.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
