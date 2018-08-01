# Conductor: Desafio Limites

## Sobre a Atividade
Este repositório contém a atividade proposta no [DOC.md](https://github.com/nycholassousa/desafio-limites/blob/master/DOC.md) fornecido.

## Requisitos

- JDK 1.8+
- Maven 3+
- JUnit 4+

## Executando a Atividade

A atividade foi realizada usando o IntelliJ IDEA, porém, para executar, era usado apenas o comando do plugin jetty:

```sh
	cd to-project
	mvn jetty:run
```

Assim, para executar o projeto, deve-se ter o maven instalado em seu computador, juntamente com as variáveis de ambiente do java e maven configuradas.

## Informações Adicionais

### Banco de Dados
A pasta resources contém o schema utilizado, para o bom funcionamento do projeto, é necessário importar tal banco de dados.

### Scheduler
Há dois scheduler, um dos tipos é um schedule a cada 30 (trinta) segundos e o outro tipo é num horário exato (12 horas da tarde), aconselho a deixar apenas 1 (um) ativo.

### Front End
Há um front-end junto com o projeto, onde o retorno é sempre um model attribute, para que o jsp "converse" com ele de simples e efetiva.