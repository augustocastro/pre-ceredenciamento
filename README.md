# API de serviços InfoBTC.

API de serviços referentes ao website InfoBTC, com operações básicas para CRUD de usuários e geração de token, usando validação de acesso via JWT.
Faz uso das seguintes tecnologias:

  - Java
  - Spring Framework
  - Postgres
  
# Pré requisitos  
  
Algumas coisas são necessárias para iniciarmos com este projeto:  
  
  - Java 8 ou superior  
  - Uma conexão PostgreSQL  
  - Maven
  - Eclipse
  
# Instalação    
1. Faça o clone deste projeto, em seguida, acesse o diretório aonde fez o clone.   
2. Após fazer isso crie um banco de dados no PostgresSQL com o nome infobtc.
3. No Arquivo application.properties altere as propriedades para as credencias do teu banco:

````
	spring.datasource.driver-class-name=org.postgresql.Driver
	spring.datasource.url=jdbc:postgresql://localhost:5432/infobtc
	spring.datasource.username=postgres
	spring.datasource.password=root
```
4. Rode o comando mvn install.
5. Rode a aplicação pelo método main definido em InfobtcApplication.java.

# Consumindo a API:
Recomendamos a utilização de um HTTP Client para a realização dos testes, sugerismo o Postman.

# Auth:
##### POST localhost:8080/auth

Endpoint rest que gera o token

```
{
	"email": "adm@gmail.com",
	"senha": "123456"
}
```

# Investidor Pessoa Jurídica:
##### POST localhost:8080/investidor-pessoa-juridica/
Cadastro de Investidor Pessoa Jurídica

````
{
    "cnpj": "00.000.000/0000-00",
    "nome": "John Doe",
    "email": "investidor@teste.com",
    "telefone": "(00) 0 0000-0000",
    "inscricao": "00000000",
    "id_consultor": 1,
    "endereco": {
        "endereco": "SCRL 709, Asa Norte",
        "cidade": "Brasília",
        "estado": "Destrito Federal",
        "pais": "Brasil",
        "cep": "00000-0000."
    }
}
````

##### PUT localhost:8080/investidor-pessoa-juridica/{id}
Edição de Investidor Pessoa Jurídica

````
{
    "cnpj": "00.000.000/0000-00 edit",
    "nome": "John Doe edit",
    "email": "investidor@teste.com edit",
    "telefone": "(00) 0 0000-0000 edit",
    "inscricao": "00000000 edit",
    "id_consultor": 1,
    "endereco": {
        "endereco": "SCRL 709, Asa Norte edit",
        "cidade": "Brasília edit",
        "estado": "Destrito Federal edit",
        "pais": "Brasil edit",
        "cep": "00000-0000. edit"
    }
}
````

##### GET http://localhost:8080/investidor-pessoa-juridica/todos
Consulta de todas todos Investidor Pessoa Jurídica

# Investidor Pessoa Física:

##### POST localhost:8080/pessoa-fisica/
Cadastro de Investidor Pessoa Física

````
{
	"cpf": "000.000.000-00",
	"dt_nascimento": "2019-07-24",
	"sexo": "MASCULINO",
	"profissao": "Programador",
	"documento": "000000",
	"orgao_emissor_uf": "PC - Goiás",
	"regime_bens": "0",
	"nome": "Teste",
	"email": "email@teste.com",
	"telefone": "(00) 0 0000-0000",
	"estadoCivil": "SOLTEIRO",
	"idConsultor": 1,
	"nacionalidade": "Brasileira",
	"endereco": {
		"endereco": "SCRL 709, Asa Norte",
		"cidade": "Brasília",
		"estado": "Destrito Federal",
		"pais": "Brasil",
		"cep": "00000-0000."
	}
}
````

##### PUT localhost:8080/pessoa-fisica/{id}
Edição de Investidor Pessoa Física

````
{
	"cpf": "000.000.000-00 edit",
	"dt_nascimento": "2019-07-24",
	"sexo": "MASCULINO",
	"profissao": "Programador edit",
	"documento": "000000 edit",
	"orgao_emissor_uf": "PC - Goiás edit",
	"regime_bens": "0",
	"nome": "John Doe edit",
	"email": "email@teste.com edit",
	"telefone": "(00) 0 0000-0000 edit",
	"estadoCivil": "CASADO",
	"idConsultor": 1,
	"nacionalidade": "Brasileira edit",
	"endereco": {
		"endereco": "SCRL 709, Asa Norte edit",
		"cidade": "Brasília edit",
		"estado": "Destrito Federal edit",
		"pais": "Brasil edit",
		"cep": "00000-0000. edit"
	}
}
````

##### GET http://localhost:8080/investidor-pessoa-fisica/todos
Consulta de todos Investidor Pessoa Física

# Pessoa:
##### GET http://localhost:8080/investidor/todos
Consulta de todos Investidor

##### GET http://localhost:8080/investidor/{id}
Consulta de Investidor por id

##### DELETE http://localhost:8080/investidor/{id}
Exclusão de Investidor


# Perfil
##### POST localhost:8080/perfil
Cadastro de Perfil

```
{
	"nome": "adm"
}
```

##### PUT localhost:8080/perfil/{id}
Edição de Perfil

```
{
	"nome": "adm edit"
}
```

##### GET http://localhost:8080/perfil/todos
Consulta de todos os Perfil.

##### GET http://localhost:8080/perfil/{id}
Consulta de Perfil por id

##### DELETE http://localhost:8080/perfil/{id}
Exclusão de Perfil por id

# Consultor
##### POST localhost:8080/consultor
Cadastro de Consultor

```
{
	"nome": "John Doe",
	"telefone": "(00) 0000-0000",
	"usuario": {
		"email": "consultor@gmail.com",
		"senha": "123",
		"perfis": [1]
	},
	"endereco": {
		"endereco": "SCRL 709, Asa Norte",
		"cidade": "Brasília",
		"estado": "Destrito Federal",
		"pais": "Brasil",
		"cep": "00000-0000."
	}
}
```

##### PUT localhost:8080/consultor/{id}
Edição de Consultor

```
{
	"nome": "John Doe edit",
	"telefone": "(00) 0000-0000 edit",
	"usuario": {
		"email": "consultor@gmail.com edit",
		"senha": "123",
		"perfis": [1]
	},
	"endereco": {
		"endereco": "SCRL 709, Asa Norte edit",
		"cidade": "Brasília edit",
		"estado": "Destrito Federal edit",
		"pais": "Brasil edit",
		"cep": "00000-0000. edit"
	}
}
```
##### GET http://localhost:8080/consultor/todos
Consulta de todos os Consultor.

##### GET http://localhost:8080/consultor/{id}
Consulta de Consultor por id

##### DELETE http://localhost:8080/consultor/{id}
Exclusão de Consultor por id

