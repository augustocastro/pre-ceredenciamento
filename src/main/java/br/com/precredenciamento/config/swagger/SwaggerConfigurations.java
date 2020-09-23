package br.com.precredenciamento.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.precredenciamento.model.Endereco;
import br.com.precredenciamento.model.UsuarioExterno;
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
	public Docket precredenciamento() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.precredenciamento.controller"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Endereco.class)
				.ignoredParameterTypes(UsuarioExterno.class)
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