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

# Pessoa Jurídica:
##### POST localhost:8080/pessoa-juridica/
Cadastro de Pessoa Jurídica

````
{
	"cnpj": "00.000.000/0000-00",
	"nome": "Teste",
	"email": "email@teste.com",
	"telefone": "(00) 0 0000-0000",
	"endereco": {
		"endereco": "SCRL 709, Asa Norte",
		"cidade": "Brasília",
		"estado": "Destrito Federal",
		"pais": "Brasil",
		"cep": "00000-0000."
	}
}
````

##### PUT localhost:8080/pessoa-juridica/{id}
Edição de Pessoa Jurídica

````
{
	"cnpj": "00.000.000/0000-00",
	"nome": "Teste",
	"email": "email@teste.com",
	"telefone": "(00) 0 0000-0000",
	"endereco": {
		"endereco": "SCRL 709, Asa Norte",
		"cidade": "Brasília",
		"estado": "Destrito Federal",
		"pais": "Brasil",
		"cep": "00000-0000."
	}
}
````


# Pessoa Física:
##### POST localhost:8080/pessoa-fisica/
Cadastro de Pessoa Física

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
Edição de Pessoa Física

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
	"endereco": {
		"endereco": "SCRL 709, Asa Norte",
		"cidade": "Brasília",
		"estado": "Destrito Federal",
		"pais": "Brasil",
		"cep": "00000-0000."
	}
}
````

# Pessoa:
##### GET http://localhost:8080/pessoa/todos?pagina=0&quantidade=1
Consulta de todas as Pessoas, tanto Pessoa Física quanto Pessoa Jurídica.

##### GET http://localhost:8080/pessoa/{id}
Consulta de Pessoa por id, tanto Pessoa Física quanto Pessoas Jurídica.

##### DELETE http://localhost:8080/pessoa/{id}
Exclusão de Pessoa por id, tanto Pessoa Física quanto Pessoas Jurídica.






