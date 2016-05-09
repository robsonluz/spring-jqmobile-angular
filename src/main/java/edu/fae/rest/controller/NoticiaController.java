package edu.fae.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.fae.rest.model.Message;
import edu.fae.rest.model.Noticia;
import edu.fae.rest.repository.NoticiaRepository;

/**
 * 
 * @author robson
 *
 */
@RestController
@RequestMapping("/noticias")
public class NoticiaController {
	@Autowired NoticiaRepository noticiaRepository;

	/**
	 * @return Busca notícias
	 */
	@RequestMapping(value="/busca", method=RequestMethod.GET)
	public List<Noticia> findAprovadas(@RequestParam("texto") String texto) {
		return noticiaRepository.find("%" + texto + "%");
	}
	
	
	/**
	 * @return Retorna todas as notícias aprovadas
	 */
	@RequestMapping(value="/aprovadas", method=RequestMethod.GET)
	public List<Noticia> findAprovadas() {
		return noticiaRepository.findBySituacao("Aprovada");
	}	
	
	/**
	 * @return Retorna todas as notícias
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<Noticia> findAll() {
		return noticiaRepository.findAll();
	}
	
	/**
	 * @return Retorna uma noticia por id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Noticia show(@PathVariable Long id) {
		return noticiaRepository.findOne(id);
	}
	
	/**
	 * Insere uma notícia na base de dados
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public Noticia save(@RequestBody Noticia noticia) {
		noticiaRepository.save(noticia);
		return noticia;
	}		
	
	/**
	 * Remove uma notícia na base de dados
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Message delete(@PathVariable Long id) {
		noticiaRepository.delete(id);
		return Message.OK;
	}
	
	/**
	 * Aprova uma notícia
	 */
	@RequestMapping(value="/{id}/aprovar", method=RequestMethod.POST)
	public Noticia aprovar(@PathVariable Long id) {
		Noticia noticia = noticiaRepository.findOne(id);
		noticia.setSituacao("Aprovada");
		noticiaRepository.save(noticia);
		return noticia;
	}	
}
