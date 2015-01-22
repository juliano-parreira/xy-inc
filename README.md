# xy-inc
Zup - Processo Seletivo

### Introdução

O desenvolvimento foi feito usando:

- Java 1.7
- Eclipse Luna Release (4.4.0)
- GlassFish 3.1
- MySQL Workbench 6.2
- MySQL Community Server 5.6.12

### Banco de Dados

Crie os usuários no banco de dados usando o script:

- database/Zup_XYInc_Users.sql

Crie a tabela no banco de dados usando o script:

- database/Zup_XYInc_DDL.sql

### GlassFish

Crie as configurações de "JDBC Connection Pools" e "JDBC Resources" conforme descrito no arquivo:

- database/Zup_XYInc_config.txt

### Serviços REST

São três serviços REST, conforme descrito abaixo:

1. Cadastro de POIs:

O POI deve ser informado como JSON no corpo da requisição.
Em caso de sucesso, o serviço retorna o POI informado na requisição com o ID de registro no BD.

Requisição:
```sh
  Url: http://localhost:8080/xyincWeb/rest/v1/points
	Method: POST
	Content-Type: application/json;charset=utf-8
	Body: {"name":"Local","positionX":10,"positionY":20}
```
Resposta:
```sh
	Status: 201
	Content-Type: application/json;charset=utf-8
	Body: {"id":10,"name":"Local","positionX":10,"positionY":20}
```

2. Listar todos os POIs:

3. Listar os POIs por proximidade:



