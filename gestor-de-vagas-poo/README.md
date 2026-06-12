# Sistema de Gestao de Vagas

Projeto desenvolvido em Java para a disciplina de Programacao Orientada a Objetos.

O sistema permite cadastrar e listar candidatos, empresas e vagas de emprego. A aplicacao possui interface grafica em Java Swing, API REST com Spring Boot e persistencia de dados em banco H2 local.

## Funcionalidades principais

- Cadastro e listagem de candidatos.
- Cadastro e listagem de empresas.
- Cadastro e listagem de vagas associadas a empresas.
- Exclusao de candidatos, empresas e vagas pela interface grafica.
- Validacao de dados de entrada.
- Tratamento centralizado de erros.
- Persistencia entre execucoes usando banco H2 em arquivo.
- Dados iniciais para demonstracao na apresentacao.

## Interface grafica

A interface foi criada com Java Swing e possui tres telas principais em abas:

1. **Portal inicial**: tela de entrada com apresentacao do sistema, opcoes para candidato e empresa, estatisticas e acesso ao painel.
2. **Cadastro de candidato**: tela em formato de janela, com barra superior, etapas laterais e formulario de dados pessoais.
3. **Cadastro de empresa**: tela em formato de janela, com etapas laterais, dados da empresa, contato e descricao.
4. **Cadastro de vaga**: card centralizado para descricao, nivel, beneficios e empresa responsavel.
5. **Painel de vagas**: menu lateral, tabela de vagas, acao para criar nova vaga e exclusao.
6. **Listagens auxiliares**: tabelas de candidatos e empresas com opcoes de cadastro e exclusao.

Cada tela exibe feedback visual ao usuario em operacoes de sucesso ou erro. As operacoes de exclusao solicitam confirmacao antes de alterar os dados.

## Estrutura do sistema

```text
Interface Swing
      |
      v
Controllers REST / Use Cases
      |
      v
Repositories JPA
      |
      v
Banco H2 em arquivo
```

Materiais de apoio:

- [Diagrama de classes](docs/diagrama-classes.md)
- [Roteiro de apresentacao](docs/roteiro-apresentacao.md)

Principais classes:

- `modules.candidate.entities.CandidateEntity`: representa um candidato.
- `modules.company.entities.CompanyEntity`: representa uma empresa.
- `modules.job.entities.JobEntity`: representa uma vaga.
- `modules.shared.entities.SystemUserEntity`: classe abstrata com dados comuns de usuarios do sistema.
- `modules.candidate.usecases.CreateCandidateUseCase`: regra de negocio para cadastro de candidato.
- `modules.company.usecases.CreateCompanyUseCase`: regra de negocio para cadastro de empresa.
- `modules.job.usecases.CreateJobCompanyUseCase`: regra de negocio para cadastro de vaga.
- `ui.MainWindow`: janela principal da interface Swing.
- `config.DemoDataInitializer`: cria dados iniciais quando o banco esta vazio.

Organizacao dos modulos:

```text
modules/
  candidate/
    controllers/
    entities/
    repositories/
    usecases/
  company/
    controllers/
    entities/
    repositories/
    usecases/
  job/
    controllers/
    entities/
    repositories/
    usecases/
  shared/
    entities/
```

## Conceitos de POO aplicados

- **Classes e objetos**: candidatos, empresas e vagas sao representados por classes do dominio.
- **Encapsulamento**: atributos privados com acesso controlado por metodos.
- **Heranca**: `CandidateEntity` e `CompanyEntity` herdam de `SystemUserEntity`, pois ambos possuem dados comuns como nome, usuario, e-mail, senha e descricao.
- **Classes abstratas**: `SystemUserEntity` concentra somente os atributos compartilhados por usuarios do sistema e nao deve ser instanciada diretamente.
- **Composicao/associacao**: uma vaga pertence a uma empresa.
- **Interfaces**: repositories usam interfaces do Spring Data JPA.
- **Excecoes**: erros de negocio, como usuario duplicado, sao tratados com excecoes especificas.
- **Polimorfismo**: o sistema utiliza repositories por suas interfaces, permitindo que o Spring forneca implementacoes em tempo de execucao.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Java Swing
- H2 Database
- Maven
- Lombok
- Jakarta Validation
- OpenAPI/Swagger

## Como executar

### Requisitos

- JDK 21 instalado.
- Maven Wrapper incluido no projeto.

### Rodar pelo Maven

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

### Gerar o JAR executavel

```bash
./mvnw package
```

No Windows:

```bash
mvnw.cmd package
```

O arquivo sera gerado em:

```text
target/gestor-de-vagas-poo-0.0.1-SNAPSHOT.jar
```

### Executar o JAR

```bash
java -jar target/gestor-de-vagas-poo-0.0.1-SNAPSHOT.jar
```

Ao iniciar, a aplicacao abre a interface Swing e tambem disponibiliza a API REST localmente.

## Persistencia de dados

O sistema usa banco H2 em arquivo. Os dados ficam no diretorio:

```text
data/
```

Se o banco estiver vazio, a classe `DemoDataInitializer` cria automaticamente dados iniciais para demonstracao.

## Endpoints REST

- `POST /candidate/`: cadastra candidato.
- `POST /company/`: cadastra empresa.
- `POST /job/`: cadastra vaga.

Documentacao da API:

```text
http://localhost:8080/swagger-ui.html
```

## Testes

Para executar os testes:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```
