package edu.fae.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import edu.fae.rest.model.Noticia;

/**
 * 
 * @author robson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NoticiasServiceApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0"})
public class NoticiaRestTests {
	@Value("${local.server.port}")
	private int port;
	
	private RestTemplate template = new TestRestTemplate();
	
	@Test
	public void testFindAll() {
		String response = template.getForObject("http://localhost:" + port + "/noticias", String.class);
		System.out.println(response);
	}
	
	@Test
	public void testShow() {
		String response = template.getForObject("http://localhost:" + port + "/noticias/1", String.class);
		System.out.println(response);
	}	
	
	@Test
	public void testCreate() {
		Noticia not = new Noticia();
		not.setTitulo("Noticia teste");
		not.setTexto("Texto da Noticia");
		
		String response = template.postForObject("http://localhost:" + port + "/noticias", not, String.class);
		System.out.println(response);
	}	
	
	@Test
	public void testUpdate() {
		Noticia not = new Noticia();
		not.setId(1L);
		not.setTitulo("Novo titulo");
		not.setTexto("Novo texto");
		
		String response = template.postForObject("http://localhost:" + port + "/noticias", not, String.class);
		System.out.println(response);
	}
	
	
	@Test
	public void testDelete() {
		template.delete("http://localhost:" + port + "/noticias/2");
	}
	
}
