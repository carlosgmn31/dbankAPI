# API do DBank - Simulação de Gerenciamento de Clientes e Contas Digitais

O DBank é um sistema simulado para gerenciar clientes e suas contas digitais. Ele armazena as informações em um banco de dados relacional e expõe serviços REST para acesso. Abaixo estão os detalhes da API para interagir com o DBank.

## Recursos Disponíveis

1. **Clientes**
    - Criar uma conta
    - Efetuar depósito em uma conta
    - Consultar saldo de conta
    - Realizar saque de conta
    - Bloquear conta
    - Recuperar extrato de conta
    - Extrato por período

## Documentação da API

A API está documentada com Swagger. Você pode acessar a documentação através do seguinte link: http://localhost:8080/swagger-ui/index.html

## Instruções de Execução

### Pré-requisitos:

- Docker / Docker Desktop
- IDE (Eclipse, IntelliJ, VSCode, ou qualquer outra com capacidade de rodar projetos Java)
- DBeaver (opcional)

### Passo a Passo:

1. Clone o repositório do DBank.
2. No terminal, navegue até o diretório raiz do projeto.
3. Execute o seguinte comando para iniciar os containers do Docker contendo o PHPMyAdmin e o MySQL (pré-configurado para rodar em http://localhost:8000/):

    ```
    docker-compose -f docker-compose.yml up -d
    ```

4. Acesse o PHPMyAdmin através do navegador e insira as credenciais que estão no arquivo `docker-compose.yml`. Você também pode editar as portas e credenciais conforme sua preferência/disponibilidade.
5. Execute o projeto em sua IDE Java.
6. Você agora pode interagir com a API do DBank normalmente.

Lembre-se de consultar a documentação da API para mais detalhes sobre os endpoints e seus parâmetros.
