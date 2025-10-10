# Universidade-Spring

Esse é um app para gerenciar uma universidade, feito com Spring Boot e JPA/Hibernate. Tudo já está pronto pra rodar em Docker, então fica fácil de usar em qualquer lugar.

## O que você precisa

- Docker e Docker Compose instalados e funcionando.

## Como rodar o projeto

1. Clone o repositório aí no seu computador.
2. Abra o terminal na pasta do projeto.
3. Rode o comando abaixo pra subir tudo:

    ```bash
    docker compose up --build
    ```

    Na primeira vez pode demorar um pouco, porque o Docker vai baixar as imagens e montar tudo.

## Onde acessar

- **Swagger (documentação e testes da API):**  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Banco de Dados (PostgreSQL):**  
  Está rodando na porta 5432 do seu computador.

## Como parar tudo

No terminal onde você rodou o comando, aperte `Ctrl + C`.

Se quiser remover os containers, use:

```bash
docker compose down
```