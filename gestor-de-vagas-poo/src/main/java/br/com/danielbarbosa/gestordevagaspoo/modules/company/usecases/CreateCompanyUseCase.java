package br.com.danielbarbosa.gestordevagaspoo.modules.company.usecases;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.UserFoundException;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.entities.CompanyEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.repositories.CompanyRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.shared.validators.SystemUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        SystemUserValidator.validate(companyEntity);

        this.companyRepository
                .findByUsernameOrEmail(
                        companyEntity.getUsername(),
                        companyEntity.getEmail()
                )
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);
    }
}
