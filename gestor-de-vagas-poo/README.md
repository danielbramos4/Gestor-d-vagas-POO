# Sistema de Gestão de Vagas

##  Sobre o Projeto

O Sistema de Gestão de Vagas é uma aplicação desenvolvida em Java utilizando o framework Spring Boot, com o objetivo de gerenciar candidatos, empresas e vagas de emprego.

O sistema foi construído seguindo os princípios da Programação Orientada a Objetos (POO) e uma arquitetura em camadas, proporcionando organização, reutilização de código e facilidade de manutenção.

---

#  Objetivos

O sistema tem como finalidade:

* Cadastrar candidatos.
* Validar dados dos candidatos.
* Evitar cadastros duplicados.
* Gerenciar empresas.
* Gerenciar vagas de emprego.
* Relacionar vagas às empresas.
* Fornecer uma API REST para integração com aplicações externas.

---

#  Tecnologias Utilizadas

| Tecnologia         | Finalidade                    |
| ------------------ | ----------------------------- |
| Java 25            | Linguagem principal           |
| Spring Boot        | Framework principal           |
| Spring Web         | Criação da API REST           |
| Spring Data JPA    | Persistência de dados         |
| PostgreSQL         | Banco de dados                |
| Jakarta Validation | Validação dos dados           |
| Lombok             | Redução de código boilerplate |
| OpenAPI / Swagger  | Documentação da API           |
| Maven              | Gerenciamento de dependências |

---


#  Arquitetura do Sistema

O projeto segue uma arquitetura baseada em camadas:

```text
Cliente
   │
   ▼
Controller
   │
   ▼
UseCase
   │
   ▼
Repository
   │
   ▼
Banco de Dados
```

### Controller

Responsável por receber as requisições HTTP.

### UseCase

Responsável pelas regras de negócio.

### Repository

Responsável pela comunicação com o banco de dados.

### Entity

Representa os objetos do domínio da aplicação.

---

#  CandidateEntity

Representa um candidato cadastrado na plataforma.

## Atributos

| Campo       | Tipo          |
| ----------- | ------------- |
| id          | UUID          |
| name        | String        |
| username    | String        |
| email       | String        |
| password    | String        |
| description | String        |
| curriculum  | String        |
| createdAt   | LocalDateTime |

## Validações

### Username

Não pode conter espaços.

### E-mail

Deve possuir formato válido.

### Senha

Deve possuir entre 10 e 100 caracteres.

---

#  CompanyEntity

Representa uma empresa cadastrada no sistema.

## Atributos

| Campo       | Tipo          |
| ----------- | ------------- |
| id          | UUID          |
| username    | String        |
| email       | String        |
| password    | String        |
| website     | String        |
| name        | String        |
| description | String        |
| createdAt   | LocalDateTime |

---

#  JobEntity

Representa uma vaga de emprego.

## Atributos

| Campo       | Tipo          |
| ----------- | ------------- |
| id          | UUID          |
| description | String        |
| benefits    | String        |
| level       | String        |
| companyId   | UUID          |
| createdAt   | LocalDateTime |

## Relacionamento

Uma vaga pertence a uma empresa.

Vaga 
    ──────────> Empresa


#  CandidateRepository

Interface responsável pela persistência dos candidatos.

Herda funcionalidades do:

```
JpaRepository<CandidateEntity, UUID>
```

## Método Personalizado

```
findByUsernameOrEmail(String username, String email)
```

Utilizado para verificar se já existe um candidato cadastrado com o mesmo username ou e-mail.

---

#  CreateCandidateUseCase

Responsável pela regra de negócio de cadastro de candidatos.

## Fluxo de Execução

1. Recebe os dados do candidato.
2. Verifica se o username já existe.
3. Verifica se o e-mail já existe.
4. Caso exista, lança uma exceção.
5. Caso não exista, salva o candidato.

---

#  CandidateController

Responsável por receber as requisições da API.

## Endpoint Disponível

### Cadastro de Candidato

```http
POST /candidate/
```

## Exemplo de Requisição


  "name": "João Silva",

  "username": "joaosilva",

  "email": "joao@email.com",

  "password": "1234567890",

  "description": "Desenvolvedor Java",

  "curriculum": "curriculo.pdf"



---

# Tratamento de Exceções

## UserFoundException

Exceção lançada quando já existe um usuário cadastrado.


throw new UserFoundException();


Mensagem retornada: [Usuário já existe]


## ErrorMessageDTO

Classe utilizada para padronizar mensagens de erro.

### Campos

| Campo   | Tipo   |
| ------- | ------ |
| message | String |
| field   | String |

Exemplo:

{

"message": "O campo email deve conter um e-mail válido",

  "field": "email"

}


---

## ExceptionHandlerController

Responsável pelo tratamento global das exceções de validação.

Intercepta:

```
MethodArgumentNotValidException
```

Retornando mensagens amigáveis para o usuário.

---

#  Fluxo Completo de Cadastro

```text
Cliente
   │
   ▼
POST /candidate
   │
   ▼
CandidateController
   │
   ▼
CreateCandidateUseCase
   │
   ├── Verifica username
   ├── Verifica email
   │
   ▼
CandidateRepository
   │
   ▼
PostgreSQL
```

---

#  Conceitos de POO Aplicados

## Encapsulamento

O encapsulamento consiste em proteger os atributos de uma classe, permitindo o acesso a eles apenas por meio de métodos apropriados.
seus beneficios são uma maior proteção de dados, controle de acesso dos atributos e ajuda na organização do código.
## Abstração

A abstração consiste em representar objetos do mundo real através de classes.
No projeto:
```
* CandidateEntity
* CompanyEntity
* JobEntity
```
## Associação

A associação representa o relacionamento entre objetos.
No sistema existe um relacionamento entre empresas e vagas:
```
@ManyToOne
@JoinColumn(name = "company_id")
private CompanyEntity companyEntity;
```
Isso significa que uma empresa pode possuir várias vagas.
Cada vaga pertence a apenas uma empresa.

## Polimorfismo

O polimorfismo ocorre quando um objeto pode assumir diferentes formas através da herança ou implementação de interfaces.

Um exemplo presente no projeto é:
```
public interface CandidateRepository
extends JpaRepository<CandidateEntity, UUID> {
}
```
O Spring cria automaticamente uma implementação dessa interface em tempo de execução.

Assim, o sistema utiliza o objeto através da interface:
```
@Autowired
private CandidateRepository candidateRepository;
```
## Reutilização

Uso de:

* JpaRepository
* Lombok
* Spring Boot

---

## Conclusão

O Sistema de Gestão de Vagas foi desenvolvido utilizando Programação Orientada a Objetos e Spring Boot, aplicando conceitos de arquitetura em camadas, validação de dados, persistência com JPA e tratamento centralizado de exceções.

A estrutura adotada facilita a manutenção, evolução e escalabilidade do sistema, permitindo a implementação de novas funcionalidades de forma organizada.
