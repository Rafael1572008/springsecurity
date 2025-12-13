# Spring Security | JWT

## Virtualização / WSL

Desativa o Hyper-V (requer reinicialização)
```
bcdedit /set hypervisorlaunchtype off
```

Reativa o Hyper-V (requer reinicialização)
```
bcdedit /set hypervisorlaunchtype auto
```

Lista distribuições WSL e versões
```
wsl --list --verbose
```


## Postman
- <a href="https://solar-meteor-573415.postman.co/workspace/Cartola-API~bdb8956c-c6ab-42c2-9605-e7998153e248/request/43462442-0694cc0a-7c56-4566-bd7f-7b090de7c895?action=share&creator=43462442&ctx=documentation">Rotas disponíveis</a>


## Docker Compose
Inicia todos os serviços em segundo plano (detached)
```
docker compose up -d
```

Para os containers sem apagar os dados
```
docker compose down
```

- Para tudo e remove volumes
- Apaga completamente os dados do banco
- Banco será recriado do zero na próxima execução
```
docker compose down -v
```


## Docker
Lista containers em execução
```
docker ps
```
