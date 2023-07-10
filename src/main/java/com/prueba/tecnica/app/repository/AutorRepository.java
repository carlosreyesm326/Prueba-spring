package com.prueba.tecnica.app.repository;

import com.prueba.tecnica.app.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

}
