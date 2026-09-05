# Java Socket Server Client

Projeto para estudo de Redes de Computadores utilizando Java e sockets.

## Objetivo

Implementar uma aplicação cliente-servidor utilizando TCP, com suporte a múltiplos clientes através de um thread pool.

## Estrutura

```text
src/
├── Server.java
├── Client.java
└── ClientHandler.java
```

## Protocolo

* `echo <mensagem>` → retorna a mensagem.
* `quit` → encerra a conexão.
* Outros comandos → inválidos.

## Como executar

Execute primeiro o `Server.java` e, em seguida, o `Client.java`.

Exemplo:

```text
echo hello
Response: hello

quit
```
