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

# Swagger
A partir de agora a documentação será gerada com Swagger.

Para acessar a documentação da API rode a aplicação e acesse: http://localhost:8080/swagger-ui.html

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

# Cadastro Pessoa Jurídica:
##### POST localhost:8080/investidor-pessoa-juridica/
Cadastro de Investidor Pessoa Jurídica

Obs: Enviar um FormDate sem Content-Type.

````
investidor = {
    "cnpj": "00.000.000/0000-00",
    "nome": "John Doe",
    "email": "investidor@teste.com",
    "telefone": "(00) 0 0000-0000",
    "inscricao": "00000000",
    "facebook": "https://www.facebook.com/johndoe",
    "instragam": "johndoe",
    "declaracao_licitude": false,
    "declaracao_politicamente_exposta" true: 
    "endereco": {
        "endereco": "SCRL 709, Asa Norte",
        "cidade": "Brasília",
        "estado": "Destrito Federal",
        "pais": "Brasil",
        "cep": "00000-0000."
    }
}

arquivos = [files]
````


##### GET http://localhost:8080/investidor-pessoa-juridica/todos
Consulta de todas todos Investidor Pessoa Jurídica

# Cadastro Investidor Pessoa Física:

##### POST localhost:8080/pessoa-fisica/
Cadastro de Investidor Pessoa Física

Obs: Enviar um FormDate sem Content-Type.

````
investidor = {
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
	"nacionalidade": "Brasileira",
	"facebook": "https://www.facebook.com/johndoe",
	"instragam": "johndoe",
	"declaracao_licitude": false,
	"declaracao_politicamente_exposta" true: 
	"endereco": {
		"endereco": "SCRL 709, Asa Norte",
		"cidade": "Brasília",
		"estado": "Destrito Federal",
		"pais": "Brasil",
		"cep": "00000-0000."
	}
}

arquivos = [files]
````


