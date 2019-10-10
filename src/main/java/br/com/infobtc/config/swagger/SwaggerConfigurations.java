package br.com.infobtc.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.infobtc.model.Banco;
import br.com.infobtc.model.Consultor;
import br.com.infobtc.model.Conta;
import br.com.infobtc.model.Contrato;
import br.com.infobtc.model.ContratoInvestimento;
import br.com.infobtc.model.ContratoReinvestimento;
import br.com.infobtc.model.DadosHash;
import br.com.infobtc.model.Endereco;
import br.com.infobtc.model.Fornecedor;
import br.com.infobtc.model.Funcionalidade;
import br.com.infobtc.model.InvestidorPessoaFisica;
import br.com.infobtc.model.InvestidorPessoaJuridica;
import br.com.infobtc.model.Perfil;
import br.com.infobtc.model.Usuario;
import br.com.infobtc.repository.ContaRepository;
import br.com.infobtc.repository.DadosHashRepository;
import br.com.infobtc.repository.FuncionalidadeRepository;
import br.com.infobtc.repository.PerfilRepository;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket infobtc() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.infobtc.controller"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Banco.class)
				.ignoredParameterTypes(Consultor.class)
				.ignoredParameterTypes(Contrato.class)
				.ignoredParameterTypes(ContratoInvestimento.class)
				.ignoredParameterTypes(ContratoReinvestimento.class)
				.ignoredParameterTypes(Endereco.class)
				.ignoredParameterTypes(InvestidorPessoaFisica.class)
				.ignoredParameterTypes(InvestidorPessoaJuridica.class)
				.ignoredParameterTypes(Usuario.class)
				.ignoredParameterTypes(Perfil.class)
				.ignoredParameterTypes(PerfilRepository.class)
				.ignoredParameterTypes(DadosHashRepository.class)
				.ignoredParameterTypes(DadosHash.class)
				.ignoredParameterTypes(Conta.class)
				.ignoredParameterTypes(ContaRepository.class)
				.ignoredParameterTypes(Fornecedor.class)
				.ignoredParameterTypes(Funcionalidade.class)
				.ignoredParameterTypes(FuncionalidadeRepository.class)
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.description("Header para token JWT")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build(),
						new ParameterBuilder()
						.name("HashCode")
						.description("Header para Hash Code")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build()));
	}

}