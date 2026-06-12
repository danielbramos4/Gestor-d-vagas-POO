package br.com.danielbarbosa.gestordevagaspoo.modules.company.entities;

import br.com.danielbarbosa.gestordevagaspoo.modules.shared.entities.SystemUserEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "company")
public class CompanyEntity extends SystemUserEntity {

    private String website;
}
