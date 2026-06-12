package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.entities;

import br.com.danielbarbosa.gestordevagaspoo.modules.shared.entities.SystemUserEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "candidate")
public class CandidateEntity extends SystemUserEntity {

    private String curriculum;
}
