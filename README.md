# Projeto API Casa do Código (CDC)

Este projeto é uma implementação da API **Casa do Código** com **Spring Boot**. Ele foi desenvolvido como parte de um desafio, com o objetivo de gerenciar informações sobre livros, autores e outras funcionalidades relacionadas ao catálogo da casa do código.

## Tecnologias Utilizadas

- **Java 8**
- **Spring Boot 2.3.9.RELEASE**
- **Spring Data JPA** para interação com o banco de dados.
- **Spring Web** para construção da API RESTful.
- **MySQL** como banco de dados.
- **Dropbox SDK** para integração com o Dropbox.
- **Jackson Datatype JSR310** para suporte ao mapeamento de datas.

## Dependências

- **spring-boot-starter-data-jpa**: Para integração com o JPA (Java Persistence API).
- **spring-boot-starter-web**: Para construção de APIs RESTful.
- **dropbox-core-sdk**: Para integração com o Dropbox.
- **spring-boot-devtools**: Ferramentas de desenvolvimento (habilitar reinício automático da aplicação).
- **mysql-connector-java**: Conector JDBC para o MySQL.
- **spring-boot-starter-validation**: Para realizar validações automáticas dos dados.
- **jackson-datatype-jsr310**: Suporte para mapeamento de tipos de data do Java 8.
- **spring-boot-starter-test**: Dependência para testes unitários e de integração.

## Como Rodar o Projeto

### Pré-requisitos

- **Java 8** ou superior
- **Maven** instalado
- **MySQL** configurado e rodando

### Passos

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/cdc.git
    ```

2. Navegue até a pasta do projeto:

    ```bash
    cd cdc
    ```

3. Compile o projeto e baixe as dependências com Maven:

    ```bash
    mvn clean install
    ```

4. Configure a conexão com o banco de dados no arquivo `application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/cdc
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
    ```

5. Para rodar o projeto, execute o seguinte comando:

    ```bash
    mvn spring-boot:run
    ```

6. A aplicação estará disponível em `http://localhost:8080`.

## Testes

Para rodar os testes unitários e de integração, execute:

```bash
mvn test
