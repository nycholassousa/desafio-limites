# Conductor: Desafio Limites

## Sobre a Atividade
Este repositório contém a atividade proposta no [DOC.md](https://github.com/nycholassousa/desafio-limites/blob/master/DOC.md) fornecido.

## Requisitos

- JDK 1.8+
- Maven 3+
- JUnit 4+

## Executando a Atividade

### Windows

A atividade foi realizada utilizando a IDE IntelliJ, caso queira usar outra IDE, apenas importe o projeto.

### Linux

Para o linux, execute os seguintes comandos:

```sh
	cd to-project
	mvn install
	./mvnw clean package
```

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

### Gerando Dados
O arquivo [Application.java](https://github.com/nycholassousa/desafio-limites/blob/master/src/main/java/com/conductor/limites/Application.java) possui o método run(), nele irá executar duas funções: limpar e popular a database.

### Scheduler
Há dois scheduler no arquivo [ScheduledTasks](https://github.com/nycholassousa/desafio-limites/blob/master/src/main/java/com/conductor/limites/component/ScheduledTasks.java), um dos tipos é um schedule a cada 30 segundos e o outro tipo é num horário exato (12h da tarde)