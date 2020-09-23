package br.com.precredenciamento.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.authorizeRequests().antMatchers("/usuario-externo/**", "/dependente/**", "/auth/**").permitAll().anyRequest().authenticated();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userDnPatterns("cn={0},c=Users,dc=sesc_df,dc=com")
//				.contextSource(contextSource()).passwordCompare().passwordEncoder(new LdapShaPasswordEncoder())
//				.passwordAttribute("SenhaAD");

//		auth.ldapAuthentication().userDnPatterns("cn={0},cn=Users").contextSource()
//				.managerDn("cn=otrs,cn=Users,dc=sesc_df,dc=com")
//				.managerPassword("OterS2018")
//				.url("ldap://10.1.203.1:389/dc=sesc_df,dc=com").and().passwordCompare()
//				.passwordAttribute("SenhaAD");
		
        auth.authenticationProvider(ldapAuthenticationProvider());
	}

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Bean
//	public DefaultSpringSecurityContextSource contextSource() {
//		return new DefaultSpringSecurityContextSource(Collections.singletonList("ldap://10.1.203.1:389"),
//				"dc=sesc_df,dc=com");
//	}
	
	@Bean
	public AuthenticationProvider ldapAuthenticationProvider() throws Exception {
	        String ldapServerUrl = "ldap://10.1.203.1:389/dc=sesc_df,dc=com";
	        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(ldapServerUrl);
	        String ldapManagerDn = "cn=otrs,cn=Users,dc=sesc_df,dc=com";
	        contextSource.setUserDn(ldapManagerDn);
	        String ldapManagerPassword = "OterS2018";
	        contextSource.setPassword(ldapManagerPassword);
	        contextSource.setReferral("follow");
	        contextSource.afterPropertiesSet();
	        LdapUserSearch ldapUserSearch = new FilterBasedLdapUserSearch("", "cn={0}", contextSource);
	        BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
	        bindAuthenticator.setUserSearch(ldapUserSearch);
	        LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(bindAuthenticator, new DefaultLdapAuthoritiesPopulator(contextSource, ""));
	        return ldapAuthenticationProvider;
	    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**", "/h2-console/**");
	}

}
