# Cineverse

Cineverse é uma API backend construída em Java com Spring Boot para gerenciar um catálogo de filmes, categorias e serviços de streaming. A aplicação permite cadastrar usuários, autenticar via JWT e manter o relacionamento entre filmes, categorias e plataformas de streaming de forma organizada.

## Descrição

A aplicação resolve o problema de gerenciar um catálogo de conteúdos audiovisuais para um sistema de recomendação ou catálogo interno. Ela suporta:

- cadastro e login de usuários;
- CRUD de filmes;
- cadastro e consulta de categorias e serviços de streaming;
- relacionamento entre filmes, categorias e streamings.

O contexto de uso é um backend REST para um serviço de catálogo de mídia ou aplicação de streaming, oferecendo endpoints seguros para consumo por frontends ou integrações.

## Tecnologias e Padrões

- Java 17
- Spring Boot 3.5.11
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Flyway para versionamento de banco de dados
- PostgreSQL
- Lombok
- Maven

Padrões arquiteturais observados:

- Arquitetura em camadas (Controller, Service, Repository, Entity, DTO, Mapper)
- Separação entre modelo de domínio e DTOs de requisição/resposta
- Uso de DTOs para validação e composição de payloads
- Tratamento global de exceções via `@RestControllerAdvice`

## Arquitetura e Decisões de Design

A aplicação adota uma estrutura clara de responsabilidade:

- `controller/`: expõe endpoints REST e valida dados de entrada.
- `service/`: contém regras de negócio e coordena dependências de repositórios.
- `repository/`: abstrai acesso a dados com Spring Data JPA.
- `entity/`: modelos JPA para persistência.
- `mapper/`: converte entre DTOs e entidades.
- `config/`: configurações de segurança e JWT.

### Modelagem de domínio

- `Movie` possui relacionamentos `@ManyToMany` com `Category` e `Streaming`.
- `User` armazena credenciais e implementa `UserDetails` para integração com Spring Security.
- `Category` e `Streaming` são recursos simples que suportam cadastro e exclusão.

### Segurança e autenticação

- Autenticação JWT.
- Rotas públicas: `/cineverse/auth/register` e `/cineverse/auth/login`.
- Todas as outras rotas exigem token Bearer no header `Authorization`.
- Configuração de sessão sem estado (`SessionCreationPolicy.STATELESS`).

### Confiabilidade e concorrência

- A persistência é gerenciada por JPA/Hibernate com suporte a transações do Spring.
- O projeto usa validação de payloads com `jakarta.validation` para evitar dados inválidos.
- A prevenção de inconsistências em relacionamentos entre filmes, categorias e streamings é feita por meio de consultas de existência antes da persistência.

### Mensageria e assincronismo

- Não há filas ou mensageria assíncrona implementadas neste projeto.

## Pré-requisitos

- JDK 17
- Maven 3.x
- Docker (opcional, recomendado para o banco de dados)
- PostgreSQL (ou usar Docker Compose)

## Configuração Local

1. Crie um arquivo `.env` na raiz do projeto ou exporte variáveis de ambiente:

```bash
DB_PORT=5432
DB_LOCAL=cineverse_db
DB_USER=postgres
DB_PASSWORD=postgres
JWT_SECRET=uma_chave_secreta_segura
```

2. Suba o banco de dados PostgreSQL com Docker Compose:

```bash
docker compose up -d
```

3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

4. A aplicação estará disponível em:

```
http://localhost:8080
```

### Observações

- O Flyway executa as migrações automaticamente no startup.
- O arquivo `application.yaml` já está configurado para usar variáveis de ambiente para conexão com o banco.

## Documentação da API

### Autenticação

#### Registrar usuário

- `POST /cineverse/auth/register`
- Corpo JSON:

```json
{
  "name": "Fulano",
  "email": "fulano@example.com",
  "password": "senha123"
}
```

- Resposta de sucesso: `201 Created`

```json
{
  "name": "Fulano",
  "email": "fulano@example.com"
}
```

#### Login

- `POST /cineverse/auth/login`
- Corpo JSON:

```json
{
  "email": "fulano@example.com",
  "password": "senha123"
}
```

- Resposta de sucesso: `200 OK`

```json
{
  "token": "<JWT_TOKEN>"
}
```

### Movies

> Todas as rotas abaixo exigem header `Authorization: Bearer <JWT_TOKEN>`.

#### Listar filmes

- `GET /cineverse/movie`
- Resposta: `200 OK`

#### Consultar filme por ID

- `GET /cineverse/movie/{id}`
- Resposta: `200 OK` ou `404 Not Found`

#### Buscar filmes por categoria

- `GET /cineverse/movie/search?category={categoryId}`
- Resposta: `200 OK`

#### Criar filme

- `POST /cineverse/movie`
- Corpo JSON:

```json
{
  "title": "Matrix",
  "description": "Um hacker descobre a realidade...",
  "releaseDate": "31/03/1999",
  "rating": 8.7,
  "categories": [1, 2],
  "streamings": [1]
}
```

- Resposta: `201 Created`

#### Atualizar filme

- `PUT /cineverse/movie/{id}`
- Corpo JSON igual ao de criação
- Resposta: `200 OK` ou `404 Not Found`

#### Deletar filme

- `DELETE /cineverse/movie/{id}`
- Resposta: `204 No Content`

### Category

#### Listar categorias

- `GET /cineverse/category`

#### Consultar categoria por ID

- `GET /cineverse/category/{id}`

#### Criar categoria

- `POST /cineverse/category`
- Corpo JSON:

```json
{
  "name": "Ação"
}
```

- Resposta: `201 Created`

#### Deletar categoria

- `DELETE /cineverse/category/{id}`

### Streaming

#### Listar serviços de streaming

- `GET /cineverse/streaming`

#### Consultar streaming por ID

- `GET /cineverse/streaming/{id}`

#### Criar streaming

- `POST /cineverse/streaming`
- Corpo JSON:

```json
{
  "name": "Netflix"
}
```

- Resposta: `201 Created`

#### Deletar streaming

- `DELETE /cineverse/streaming/{id}`

## Formato de erros

- `400 Bad Request` para validações falhas ou credenciais inválidas.
- `404 Not Found` para recursos inexistentes.
- Exemplos de erro de validação retornam um JSON com campo e mensagem.

## Observações finais

- Não há documentação Swagger/OpenAPI habilitada no projeto atualmente.
- O foco do backend está na modelagem de domínio e no controle de acesso via JWT.
- Para uso em portfólio, destaque a separação em camadas, o uso de JPA para relacionamentos `ManyToMany` e a autenticação segura com Spring Security.
