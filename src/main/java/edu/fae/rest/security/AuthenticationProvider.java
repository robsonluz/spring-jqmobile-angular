package edu.fae.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import edu.fae.rest.model.Usuario;
import edu.fae.rest.repository.UsuarioRepository;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	


	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		if(!usuario.getPassword().equals(authentication.getCredentials())) {
			throw new BadCredentialsException("Usuario e/ou senha invalidos");	
		}
		return usuario;
	}



	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
	}
	
}
