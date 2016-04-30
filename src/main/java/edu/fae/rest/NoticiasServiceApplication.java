package edu.fae.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import edu.fae.rest.model.Noticia;
import edu.fae.rest.repository.NoticiaRepository;

@SpringBootApplication
public class NoticiasServiceApplication {
	
	//Dados iniciais da base de dados
	@Bean
	CommandLineRunner runner(NoticiaRepository noticiaRepository){
		return args -> {
			noticiaRepository.save(new Noticia("Notícia 1", "Texto da Notícia 1"));
			noticiaRepository.save(new Noticia("Notícia 2", "Texto da Notícia 2"));
			noticiaRepository.save(new Noticia("Notícia 3", "Texto da Notícia 3"));
			noticiaRepository.save(new Noticia("Notícia 4", "Texto da Notícia 4"));
		};
	}	

	public static void main(String[] args) {
		SpringApplication.run(NoticiasServiceApplication.class, args);
	}
	
}

@Configuration
class WebConfig extends WebMvcConfigurerAdapter {

    @Controller
    protected static class Routes {
    	@RequestMapping("/")
		public String index() {
		    return "forward:/index.html";
		}
    }
}
