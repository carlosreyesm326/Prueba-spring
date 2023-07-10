package com.prueba.tecnica.app.repository;

import com.prueba.tecnica.app.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

}
