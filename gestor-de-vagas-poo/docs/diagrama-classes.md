# Diagrama de Classes

Diagrama simplificado das principais classes do Sistema de Gestao de Vagas.

```mermaid
classDiagram
    class SystemUserEntity {
        <<abstract>>
        -UUID id
        -String name
        -String username
        -String email
        -String password
        -String description
        -LocalDateTime createdAt
    }

    class CandidateEntity {
        -String curriculum
    }

    class CompanyEntity {
        -String website
    }

    class JobEntity {
        -UUID id
        -String description
        -String benefits
        -String level
        -UUID companyId
        -LocalDateTime createdAt
    }

    class CandidateRepository {
        <<interface>>
        +findByUsernameOrEmail(String username, String email)
    }

    class CompanyRepository {
        <<interface>>
        +findByUsernameOrEmail(String username, String email)
    }

    class JobRepository {
        <<interface>>
    }

    class CreateCandidateUseCase {
        +execute(CandidateEntity candidateEntity)
    }

    class CreateCompanyUseCase {
        +execute(CompanyEntity companyEntity)
    }

    class CreateJobCompanyUseCase {
        +execute(JobEntity jobEntity)
    }

    class MainWindow {
        +show()
    }

    SystemUserEntity <|-- CandidateEntity
    SystemUserEntity <|-- CompanyEntity
    CompanyEntity "1" <-- "0..*" JobEntity : possui

    CandidateRepository ..> CandidateEntity
    CompanyRepository ..> CompanyEntity
    JobRepository ..> JobEntity

    CreateCandidateUseCase --> CandidateRepository
    CreateCompanyUseCase --> CompanyRepository
    CreateJobCompanyUseCase --> JobRepository

    MainWindow --> CreateCandidateUseCase
    MainWindow --> CreateCompanyUseCase
    MainWindow --> CreateJobCompanyUseCase
    MainWindow --> CandidateRepository
    MainWindow --> CompanyRepository
    MainWindow --> JobRepository
```

## Observacoes

- `SystemUserEntity` e uma classe abstrata porque representa dados comuns a candidatos e empresas, mas nao deve ser instanciada diretamente.
- `CandidateEntity` e `CompanyEntity` especializam `SystemUserEntity`.
- `JobEntity` se relaciona com `CompanyEntity`, pois uma vaga pertence a uma empresa.
- Os repositories sao interfaces, e suas implementacoes sao fornecidas pelo Spring Data JPA em tempo de execucao.
