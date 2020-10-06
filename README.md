# API de serviços PreCredenciamento.

API de serviços referentes ao website do sistema PreCredenciamento, que faz uso das seguintes tecnologias:

  - Java
  - Spring Framework
  - SQLServer
  
# Pré requisitos  
  
Algumas coisas são necessárias para iniciarmos com este projeto:  
  
  - Java 8 ou superior  
  - Uma conexão SQLServer  
  - Maven
  - Eclipse

# Docker
Está configurado para rodar o banco de dados com Docker caso não queira instalar na tua máquina.
1. Instale o Docker
2. Instale o Docker Compose
3. Acesse a pasta raiz do projeto e rode no terminal:

````
$ docker-compose up
```

# Instalação    
1. Faça o clone deste projeto, em seguida, acesse o diretório aonde fez o clone.   
2. Após fazer isso crie um banco de dados no SQLServer com o nome master.
3. Na classe `br.com.precredenciamento.config.database.SpringJdbcConfig` altere as propriedades do Bean anotado com @Primary para as credenciais do seu banco:

````
    @Bean(name = "dataSourcePreCredenciamento")
    @Primary
    public DataSource preCredenciamentoDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=master");
		dataSource.setUsername("sa");
		dataSource.setPassword("SA_PASSWORD=yourStrong(!)Password");
		return dataSource;
    }
```
4. Rode o comando mvn install.
5. Rode a aplicação pelo método main definido em InfobtcApplication.java.

# Swagger
A partir de agora a documentação será gerada com Swagger.

Para acessar a documentação da API rode a aplicação e acesse: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

# Consumindo a API:
Recomendamos a utilização de um HTTP Client para a realização dos testes, sugerismo o Postman.



# Atualização de Usuário Externo:

##### PUT http://localhost:8080/usuario-externo/{idUsuario}?usuario={json}
Atualização de Usuário Externo

Obs: Enviar um FormDate sem Content-Type.

````
json = {
	"id": 7,
	"cpf": "7777777777",
	"sexo": "MASCULINO",
	"nomeCompleto": "JOSÉ DA SILVA",
	"nomeSocial": "CASTRO",
	"dataNascimento": "1998-06-16",
	"estadoCivil": "SOLTEIRO",
	"grauInstrucao": "SUPERIOR",
	"rg": "55555",
	"orgaoEmissor": "PC/GO",
	"naturalidade": "CRISTALINA",
	"nacionalidade": "BRASILEIRA",
	"profissao": "PROGRAMADOR",
	"numeroCarteiraTrabalho": "1515",
	"nomePai": "SEU ZÉ",
	"nomeMae": "DONA MARIA",
	"telefoneCelular": "61999999999",
	"telefoneResidencial": "61999999999",
	"telefoneComercial": "61999999999",
	"dataAdmisao": "2019-07-24",
	"cnpjEmpregador": "11111111111111",
	"nomeEmpresa": "LightBase",
	"renda": 5000,
	"endereco": {
		"cep": "70750516",
		"endereco": "QUADRA TAL",
		"complemento": "",
		"bairro": "ASA NORTE",
		"uf": "DF",
		"cidade": "BRASÍLIA",
		"habitacao": "ALUGADA"
	}
}

arquivos = [files]
````



### Secrets

>> Importante criar as secrets (senhas, usuario e url para o properties)

    $ kubectl create secret generic accept-eula --from-literal=value=Y -n desenvolvimento

    $ kubectl create secret generic sqlserver-user-pass --from-literal=password='yourStrong(!)Password' --from-literal=user=sa -n desenvolvimento

    $ kubectl create secret generic mysql-db-url --from-literal=database=master --from-literal=url='jdbc:sqlserver://precredenciamento-sqlserver:1433;databaseName=master' -n desenvolvimento