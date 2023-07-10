package com.prueba.tecnica.app.service;


import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.utils.LibroRequestDto;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> obtenerTodosLosLibros();
    Optional<Libro> obtenerLibroPorId(Long id);
    void crearLibro(LibroRequestDto libroRequest);
    void eliminarLibro(Long idLibro);
    void actualizarLibro(LibroRequestDto libroRequest, Long idLibro);
}
