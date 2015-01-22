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

### Serviços REST + JSON

São três serviços REST + JSON, conforme descrito abaixo:

#### 1. Cadastro de POIs:

O cadastro usa método POST e o POI deve ser informado como JSON no corpo da requisição.
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
Status Codes:
```sh
- 201: POI cadastrado
- 400: parâmetro inválido ou não informado (POI)
```

#### 2. Listar todos os POIs:

A listagem usa método GET e nenhum parâmetro.
O serviço retorna JSON com a lista de POIs, caso exista algum.
Para o caso de lista vazia é retornado status 404 e o JSON padrão de erro.

Requisição:
```sh
Url: http://localhost:8080/xyincWeb/rest/v1/points
Method: GET
```
Resposta:
```sh
Status: 200
Content-Type: application/json;charset=utf-8
Body: [{"id":1,"name":"Lanchonete","positionX":27,"positionY":12},...,{"id":10,"name":"Park","positionX":58,"positionY":56}]
```
Status Codes:
```sh
- 200: lista de POIs encontrados
- 404: nenhum POI encontrado
```

#### 3. Listar os POIs por proximidade:

A listagem usa método GET e requer parâmetros obrigatórios:

- x : coordenada X do ponto de referência
- y : coordenada Y do ponto de referência
- d-max : distância máxima até o ponto de referência

O serviço retorna JSON com a lista de POIs com a distância até o ponto de referência, caso exista algum que atenda aos parâmetros informados, ordenados do mais próximo ao mais distante.
Para o caso de lista vazia é retornado status 404 e o JSON padrão de erro.

Requisição:
```sh
Url: http://localhost:8080/xyincWeb/rest/v1/points/filtered?x=20&y=10&d-max=10
Method: GET
```
Resposta:
```sh
Status: 200
Content-Type: application/json;charset=utf-8
Body: [{"id":6,"name":"Supermercado","positionX":23,"positionY":6,"distance":5.0},...,{"id":5,"name":"Pub","positionX":12,"positionY":8,"distance":8.2}]
```
Status Codes:
```sh
- 200: lista de POIs e distâncias encontrados
- 404: nenhum POI encontrado
- 400: parâmetro inválido ou não informado (x, y ou d-max)
```

### Resposta de Erro

As respostas diferentes de sucesso são padronizadas em um JSON com código de erro e uma mensagem, conforme exemplos a seguir:
```sh
Status: 404
Content-Type: application/json;charset=utf-8
Body: {"errorCode":1102,"message":"No points of interest found for given parameters (x: 100, y: 115, d-max: 1)"}
```
```sh
Status: 400
Content-Type: application/json;charset=utf-8
Body: {"errorCode":1004,"message":"Invalid parameter \"d-max\": \"-1\" (Invalid value)"}
```
```sh
Status: 405
Content-Type: application/json;charset=utf-8
Body: {"errorCode":405,"message":"METHOD_NOT_ALLOWED"}
```
