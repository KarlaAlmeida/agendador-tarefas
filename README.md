# ⏰ API de Agendamento de Tarefas

Gerencia as tarefas cadastradas por usuários, com autenticação e integração com outros serviços.

## 🚀 Funcionalidades

- Criar, atualizar, excluir e consultar tarefas
- Filtro por data e por e-mail
- Relacionamento com usuários autenticados via JWT
- Comunicação com API de usuários via FeignClient

 ## 📍 Endpoints da Aplicação
- **POST /tarefas**: Cadastra uma nova tarefa.
- **GET /tarefas/eventos**: Busca lista de tarefas por período.
- **GET /tarefas**: Busca tarefas por email.
- **DELETE /tarefas**: Deleta tarefa por ID.
- **PATCH /tarefas**: Altera status de tarefa por ID.
- **PUT /tarefas**: Atualiza tarefas por ID.

## 🔐 Segurança

- Spring Security com validação de token JWT
- Endpoints protegidos por autenticação
  
## 🔗 Integrações

- OpenFeign para comunicação com API de usuários
- Tokens JWT propagados automaticamente

## 📚 Documentação

- Swagger (Springdoc OpenAPI 3)
  - Acesso via: `/swagger-ui.html`

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- OpenFeign
- MongoDB
- Docker

## 🧱 Arquitetura

- Clean Architecture com camadas bem definidas
- Casos de uso encapsulando regras de negócio

## ✅ Boas Práticas

- Tokens verificados com `JwtUtil`
- Casos de uso isolando lógica de negócio
- Padrão de projeto com Mapper para converter objetos Entity de objetos DTO
- Requisições externas desacopladas via FeignClient
- Documentação interativa com Swagger

## 📦 Como Rodar

```bash
docker run -d -p 8081:81 agendador-tarefas
