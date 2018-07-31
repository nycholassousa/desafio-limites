# Conductor: Desafio Limites

## Sobre a Atividade
Este repositório contém a atividade proposta [neste arquivo](https://github.com/nycholassousa/desafio-limites/blob/master/DOC.md)

## Requisitos

- JDK 1.8+
- Maven 3+
- JUnit 4+

## Executando a Atividade

### Windows

A atividade foi realizada utilizando a IDE Intellij, assim, basta abrir o projeto que funcionará normalmente nesta IDE.
Para outras IDEs, aconselho a importar o projeto.

### Linux

TODO

## Serviços Disponíveis

Abaixo está os serviços disponíveis, para usuários e para as contas:

### Usuário

|                               |                                                    |
|-------------------------------|----------------------------------------------------|
| `GET /api/users`      	    | Retorna uma lista com todos os usuários            |
| `POST /api/users/new`         | Cria um novo usuário 							     |
| `GET /api/users/:id`          | Obtem informações de um usuário                    |
| `PUT /api/users/:id`          | Atualiza as informações do usuário                 |
| `DELETE /api/users/:id`       | Deleta um usuário específico                       |

### Contas

|                                  |                                                      |
|----------------------------------|------------------------------------------------------|
| `GET /api/accounts`      	       | Retorna uma lista com todas as contas                |
| `POST /api/accounts/new`         | Cria uma nova conta                                  |
| `GET /api/accounts/:id`          | Obtem informações de uma conta                       |
| `PUT /api/accounts/:id`          | Atualiza as informações de uma conta                 |
| `DELETE /api/accounts/:id`       | Deleta uma conta específica                          |


## Informações Adicionais

Toda vez que o código é executado, é criado alguns casos de testes (usuários e contas)

