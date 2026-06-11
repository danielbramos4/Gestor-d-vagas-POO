package br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.UseCases;

import br.com.danielbarbosa.gestordevagaspoo.exceptions.UserFoundException;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.CompanyEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.controllers.Company.Entities.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

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