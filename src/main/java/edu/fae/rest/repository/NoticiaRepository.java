package edu.fae.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fae.rest.model.Noticia;

/**
 * 
 * @author robson
 *
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

}
