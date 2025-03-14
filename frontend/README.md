### CDC
Este é um projeto que simula o funcionamento da casa do código. É possivel listar, cadastrar, comprar e vender livros.
O back end foi desenvolvido com base no Java/Spring e o front end Angular.

# Cdc Backend

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.0.7.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

# Cdc Frontend

# Projeto Frontend - Casa do Código (CDC Front)

Este é o frontend do sistema **Casa do Código** desenvolvido com **Angular**. A aplicação é responsável por fornecer a interface de usuário para interagir com a API backend.

## Tecnologias Utilizadas

- **Angular 6** (com `@angular/cli` e `@angular/core`).
- **Bootstrap 4.1.1** para o layout responsivo e componentes da interface.
- **Font Awesome** e **Angular FontAwesome** para ícones.
- **Ngx-Toastr** e **Angular Toastify** para notificações.
- **Ngx-Bootstrap** e **Ngx-Date-Picker** para componentes adicionais.
- **RxJS 6** para programação reativa.

## Dependências

### Principais Dependências

- **@angular/core**, **@angular/common**, **@angular/forms**: Principais módulos do Angular.
- **bootstrap**, **font-awesome**: Frameworks CSS para UI.
- **ngx-bootstrap**, **ngx-toastify**, **ngx-date-picker**: Bibliotecas adicionais para UI.
- **rxjs**: Biblioteca de programação reativa.
- **angular-toastify**: Para notificações toast.
- **json-object-mapper**: Para mapeamento de objetos JSON.

### Dependências de Desenvolvimento

- **@angular/compiler-cli**, **@angular-devkit/build-angular**: Ferramentas de compilação e desenvolvimento Angular.
- **typescript**: Para compilação de código TypeScript.
- **karma**, **jasmine**: Frameworks de teste para testes unitários e de integração.
- **protractor**: Framework para testes e2e (end-to-end).
- **tslint**: Ferramenta para linting do código TypeScript.

## Como Rodar o Projeto

### Pré-requisitos

- **Node.js** (recomendado v8 ou superior)
- **npm** (gerenciador de pacotes do Node.js)

### Passos

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/cdc-front.git
    ```

2. Navegue até a pasta do projeto:

    ```bash
    cd cdc-front
    ```

3. Instale as dependências do projeto:

    ```bash
    npm install
    ```

4. Inicie o servidor de desenvolvimento:

    ```bash
    npm start
    ```

5. A aplicação estará disponível em `http://localhost:4200`.

## Testes

Para rodar os testes unitários com Jasmine e Karma:

```bash
npm test
