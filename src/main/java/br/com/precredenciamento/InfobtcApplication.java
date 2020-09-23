package br.com.precredenciamento;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration(exclude = { EmbeddedLdapAutoConfiguration.class })
public class InfobtcApplication extends SpringBootServletInitializer implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(InfobtcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RODANDO");
	}


}
