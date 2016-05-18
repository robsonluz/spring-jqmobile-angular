package edu.fae.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import edu.fae.rest.security.AuthenticationProvider;
import edu.fae.rest.service.UsuarioService;

/**
 * Configurações de segurança
 * @author robson
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	@Autowired UsuarioService usuarioService;
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	//Autenticação de usuário administrativos
//    	auth.authenticationProvider(authenticationProvider());
//    }	
//    
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//    	return new AuthenticationProvider();
//    }    
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            ;   
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(usuarioService);
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }
    
}
