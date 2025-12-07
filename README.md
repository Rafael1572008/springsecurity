# Comandos Docker: Executar, Parar e Apagar Dados

bcdedit /set hypervisorlaunchtype off
bcdedit /set hypervisorlaunchtype auto
wsl --list --verbose


## Postman
https://solar-meteor-573415.postman.co/workspace/Cartola-API~bdb8956c-c6ab-42c2-9605-e7998153e248/request/43462442-0694cc0a-7c56-4566-bd7f-7b090de7c895?action=share&creator=43462442&ctx=documentation


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
