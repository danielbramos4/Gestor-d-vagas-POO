# Roteiro de Apresentacao

Tempo maximo: 20 minutos.

## 1. Abertura do projeto

Apresentar o sistema:

> Nosso projeto e um Sistema de Gestao de Vagas desenvolvido em Java. Ele permite cadastrar, listar e excluir candidatos, empresas e vagas de emprego, com interface grafica em Swing e persistencia em banco H2.

Pontos para mencionar:

- Tema: gestao de vagas.
- Linguagem: Java.
- Interface: Java Swing.
- Persistencia: H2 em arquivo.
- Arquitetura: camadas com entidades, controllers, use cases e repositories.

## 2. Demonstracao da aplicacao

Demonstrar as tres telas:

1. Tela de candidatos:
   - cadastrar candidato;
   - listar candidato;
   - excluir candidato com confirmacao.

2. Tela de empresas:
   - cadastrar empresa;
   - listar empresa;
   - excluir empresa com confirmacao.

3. Tela de vagas:
   - selecionar empresa;
   - cadastrar vaga;
   - listar vaga;
   - excluir vaga com confirmacao.

Destacar que o sistema mostra mensagens de sucesso e erro para o usuario.

## 3. Persistencia de dados

Explicacao sugerida:

> A persistencia foi implementada com Spring Data JPA e banco H2 em arquivo. Isso permite que os dados continuem salvos entre execucoes e facilita a apresentacao, porque o projeto roda com o JAR sem depender de um PostgreSQL instalado.

Mostrar:

- `application.properties`;
- pasta `data/`;
- `DemoDataInitializer`.

## 4. Orientacao a Objetos

Pontos principais:

- Classes e objetos:
  - `CandidateEntity`;
  - `CompanyEntity`;
  - `JobEntity`.

- Encapsulamento:
  - atributos privados;
  - acesso por getters e setters.

- Heranca e classe abstrata:
  - `SystemUserEntity` concentra os dados comuns de candidatos e empresas;
  - `CandidateEntity` e `CompanyEntity` herdam dessa classe.

- Associacao:
  - `JobEntity` possui relacao com `CompanyEntity`;
  - uma empresa pode ter varias vagas.

- Interfaces:
  - `CandidateRepository`;
  - `CompanyRepository`;
  - `JobRepository`.

- Polimorfismo:
  - o codigo usa as interfaces dos repositories;
  - o Spring fornece as implementacoes em tempo de execucao.

- Excecoes:
  - `UserFoundException`;
  - `ExceptionHandlerController`.

## 5. Qualidade do codigo

Pontos para defender:

- Projeto separado por modulos:
  - `candidate`;
  - `company`;
  - `job`;
  - `shared`.

- Cada modulo tem responsabilidades separadas:
  - `controllers`;
  - `entities`;
  - `repositories`;
  - `usecases`.

- Regras de negocio ficam nos use cases.
- Persistencia fica nos repositories.
- Interface grafica fica no pacote `ui`.
- Tratamento de erros fica centralizado em `exceptions`.

## 6. Como executar

Mostrar que o projeto pode ser executado com:

```bash
java -jar target/gestor-de-vagas-poo-0.0.1-SNAPSHOT.jar
```

Ou gerar novamente o JAR com:

```bash
mvnw.cmd package
```

## 7. Possiveis perguntas do professor

### Por que usar Spring Boot?

Porque ele facilita a organizacao em camadas, a criacao da API REST e a integracao com JPA para persistencia.

### Por que usar H2?

Porque o H2 permite persistencia em arquivo e facilita a execucao do projeto na apresentacao sem depender de um banco externo.

### Por que `SystemUserEntity` e abstrata?

Porque ela representa dados comuns entre candidatos e empresas, mas nao existe no sistema um usuario generico que deva ser cadastrado diretamente.

### Onde esta o polimorfismo?

No uso das interfaces dos repositories. O codigo depende das interfaces, e o Spring injeta implementacoes concretas em tempo de execucao.

### O projeto usa heranca de forma artificial?

Nao. A heranca foi usada apenas onde havia atributos e comportamento comum entre candidatos e empresas.

### Onde ficam as regras de negocio?

Nos use cases, como `CreateCandidateUseCase`, `CreateCompanyUseCase` e `CreateJobCompanyUseCase`.
