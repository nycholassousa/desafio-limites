# Conductor: Desafio Limites

## Sobre a Atividade
Este repositório contém a atividade proposta no [DOC.md](https://github.com/nycholassousa/desafio-limites/blob/master/DOC.md) fornecido.

## Requisitos

- JDK 1.8+
- Maven 3+
- JUnit 4+

## Executando a Atividade

### Importe o Banco de Dados

Importe o banco de dados mysql [neste arquivo](https://github.com/nycholassousa/desafio-limites/blob/master/src/main/resources/db.sql).
Neste projeto foi utilizado o phpmyadmin, fornecido pelo UniformServer

### Execute o Projeto

A atividade foi realizada usando o IntelliJ IDEA, porém, para executar, era usado apenas o comando do plugin jetty:

```sh
	cd to-project
	mvn jetty:run
```

Assim, para executar o projeto, deve-se ter o maven instalado em seu computador, juntamente com as variáveis de ambiente do java e maven configuradas.

### Usando o Projeto

Após executar o comando descrito acima, basta ir para o endereço http://localhost:8080 e você irá ver o frontend do projeto e logo após, poderá criar usuário, contas e realizar alterações nas contas.

## Informações Adicionais

### Scheduler
Há dois scheduler, um dos tipos é um schedule a cada 30 (trinta) segundos e o outro tipo é num horário exato (12 horas da tarde), aconselho a deixar apenas 1 (um) ativo.

### Front End
Há um front-end junto com o projeto, onde o retorno é sempre um model attribute, para que o jsp "converse" com ele de simples e efetiva.

### CRUD de Usuário
Devido a biblioteca do spring security, é aconselhado que as alterações no usuário sejam apenas com o usuário offline.

### CRUD de Contas
O crud de contas está completo, onde é possivel alterar seu próprio saldo, deletar uma conta, criar uma conta nova.