# Comandos Docker: Executar, Parar e Apagar Dados

## Executar o Docker Compose
Inicia todos os serviços em modo *detached*:
```bash
docker compose up -d
```

---

## Parar os Serviços
Para os containers, mas **não apaga** os dados:
```bash
docker compose down
```

---

## Parar e Apagar Todos os Dados do Banco
Para tudo **e apaga volumes**, destruindo completamente o banco:
```bash
docker compose down -v
```
Isso remove:
- Containers
- Imagens temporárias
- *Volumes* (onde ficam os dados do banco)

Banco volta como novo na próxima execução.



docker ps
