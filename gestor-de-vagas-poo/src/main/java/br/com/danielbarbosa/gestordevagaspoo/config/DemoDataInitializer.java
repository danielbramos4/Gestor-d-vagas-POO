package br.com.danielbarbosa.gestordevagaspoo.config;

import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.entities.CandidateEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.candidate.repositories.CandidateRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.entities.CompanyEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.company.repositories.CompanyRepository;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.entities.JobEntity;
import br.com.danielbarbosa.gestordevagaspoo.modules.job.repositories.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataInitializer implements CommandLineRunner {

    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    public DemoDataInitializer(
            CandidateRepository candidateRepository,
            CompanyRepository companyRepository,
            JobRepository jobRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(String... args) {
        updateOldDemoCandidate();

        if (candidateRepository.count() == 0) {
            CandidateEntity candidate = new CandidateEntity();
            candidate.setName("Candidato Demonstracao");
            candidate.setUsername("demo.candidato");
            candidate.setEmail("candidato.demo@portalvagas.com");
            candidate.setPassword("1234567890");
            candidate.setDescription("Perfil criado automaticamente para demonstracao");
            candidate.setCurriculum("curriculo-demo.pdf");
            candidateRepository.save(candidate);
        }

        if (companyRepository.count() == 0) {
            CompanyEntity company = new CompanyEntity();
            company.setName("Tech Esperanca");
            company.setUsername("techesperanca");
            company.setEmail("contato@techesperanca.com");
            company.setPassword("1234567890");
            company.setWebsite("https://techesperanca.com");
            company.setDescription("Empresa de tecnologia e recrutamento");
            companyRepository.save(company);
        }

        if (jobRepository.count() == 0 && companyRepository.count() > 0) {
            CompanyEntity company = companyRepository.findAll().get(0);

            JobEntity job = new JobEntity();
            job.setDescription("Estagio em desenvolvimento Java");
            job.setBenefits("Bolsa, mentoria e horario flexivel");
            job.setLevel("Junior");
            job.setCompanyId(company.getId());
            jobRepository.save(job);
        }
    }

    private void updateOldDemoCandidate() {
        candidateRepository
                .findByUsernameOrEmail("joaosilva", "joao@email.com")
                .ifPresent(candidate -> {
                    if ("Joao Silva".equals(candidate.getName()) && "curriculo-joao.pdf".equals(candidate.getCurriculum())) {
                        candidate.setName("Candidato Demonstracao");
                        candidate.setUsername("demo.candidato");
                        candidate.setEmail("candidato.demo@portalvagas.com");
                        candidate.setDescription("Perfil criado automaticamente para demonstracao");
                        candidate.setCurriculum("curriculo-demo.pdf");
                        candidateRepository.save(candidate);
                    }
                });
    }
}
