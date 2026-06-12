package br.com.danielbarbosa.gestordevagaspoo.modules.company.controllers;

import br.com.danielbarbosa.gestordevagaspoo.modules.company.entities.CompanyEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.usecases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        var result = this.createCompanyUseCase.execute(companyEntity);
        return ResponseEntity.ok().body(result);
    }
}
