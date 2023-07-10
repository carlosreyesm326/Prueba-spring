package com.prueba.tecnica.app.service;

import com.prueba.tecnica.app.models.Autor;
import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.utils.AutorRequestDto;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    List<Autor> obtenerTodosLosAutores();
    Optional<Autor> obtenerAutorPorId(Long id);
    void crearAutor(AutorRequestDto autorRequesDto);

    void eliminarAutor(Long idAutor);


}
