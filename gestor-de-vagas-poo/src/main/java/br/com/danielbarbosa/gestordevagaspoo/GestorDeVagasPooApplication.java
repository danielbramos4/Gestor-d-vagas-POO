package br.com.danielbarbosa.gestordevagaspoo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title ="Gestão de Vagas",
                description ="API responsavel pela gestão de vagas",version = "1"
        )
)
public class GestorDeVagasPooApplication {

	public static void main(String[] args) {
        SpringApplication.run(GestorDeVagasPooApplication.class, args);
	}

}
