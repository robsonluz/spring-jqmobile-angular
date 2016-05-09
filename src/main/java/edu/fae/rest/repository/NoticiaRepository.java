package edu.fae.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fae.rest.model.Noticia;

/**
 * 
 * @author robson
 *
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{
	
	/**
	 * Busca as Notícias por Situação
	 */
	public List<Noticia> findBySituacao(String situacao);
	
	/**
	 * Busca com HQL personalizada
	 */
	@Query("from Noticia where titulo like ?1 or texto like ?1")
	public List<Noticia> find(String texto);
	
}
