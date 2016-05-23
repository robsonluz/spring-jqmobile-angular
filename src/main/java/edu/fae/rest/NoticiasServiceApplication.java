package edu.fae.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fae.rest.model.Noticia;
import edu.fae.rest.model.Usuario;
import edu.fae.rest.repository.NoticiaRepository;
import edu.fae.rest.repository.UsuarioRepository;

@SpringBootApplication
public class NoticiasServiceApplication {
	
	//Dados iniciais da base de dados
	@Bean
	CommandLineRunner runner(NoticiaRepository noticiaRepository, UsuarioRepository usuarioRepository){
		return args -> {
			noticiaRepository.save(new Noticia("Notícia 1", "Texto da Notícia 1"));
			noticiaRepository.save(new Noticia("Notícia 2", "Texto da Notícia 2"));
			noticiaRepository.save(new Noticia("Notícia 3", "Texto da Notícia 3"));
			noticiaRepository.save(new Noticia("Notícia 4", "Texto da Notícia 4"));
			
			usuarioRepository.save(new Usuario("Usuario 1", "usuario1@email.com", "12345"));
		};
	}	

	public static void main(String[] args) {
		SpringApplication.run(NoticiasServiceApplication.class, args);
	}
	
}

