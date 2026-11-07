# API Spring Boot

API REST em Java com Spring Boot para autenticação de usuários e gerenciamento de registros consumidos pelos aplicativos React Native e Android nativo.

## Requisitos

- JDK 17
- Maven Wrapper incluído no projeto

## Execução

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Para gerar o pacote:

```powershell
.\mvnw.cmd clean package
```

A API fica disponível em `http://localhost:8080`.

## Endpoints

| Método | Rota | Finalidade |
| --- | --- | --- |
| POST | `/auth/login` | Autenticar usuário |
| POST | `/usuarios` | Cadastrar usuário |
| GET | `/registros` | Listar registros |
| GET | `/registros/{id}` | Buscar registro |
| POST | `/registros` | Criar registro |
| PUT | `/registros/{id}` | Atualizar registro |
| DELETE | `/registros/{id}` | Excluir registro |
| POST | `/teste-tcc/gerar/{quantidade}` | Gerar dados de teste |

## Tecnologias

- Spring Boot Web MVC
- Spring Data JPA
- H2 em tempo de execução
- BCrypt para senhas
- Java 17

## Configuração

As propriedades da aplicação estão em `src/main/resources/application.properties`. O banco H2 é usado para desenvolvimento e testes locais.

## Projetos consumidores

- React Native: `C:/Users/PICHAU/Desktop/VisualStudio/ReactNative`
- Android nativo: `C:/Users/PICHAU/AndroidStudioProjects/AndroidNative`
