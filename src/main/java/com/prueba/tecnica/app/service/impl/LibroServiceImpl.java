package com.prueba.tecnica.app.service.impl;

import com.prueba.tecnica.app.converter.LibroRequestDtoToLibroConverter;
import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.repository.LibroRepository;
import com.prueba.tecnica.app.service.LibroService;
import com.prueba.tecnica.app.utils.LibroRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroRequestDtoToLibroConverter libroRequestDtoToLibroConverter;
    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public void crearLibro(LibroRequestDto libroRequest) {
        Libro libroNuevo = libroRequestDtoToLibroConverter.convert(libroRequest);
        libroRepository.save(libroNuevo);


    }

    @Override
    public void eliminarLibro( Long idLibro) {
        libroRepository.deleteById(idLibro);
    }

    @Override
    public void actualizarLibro(LibroRequestDto libroRequest, Long idLibro) {
        Libro libroActualizado = libroRequestDtoToLibroConverter.convert(libroRequest);
        libroActualizado.setId(idLibro);
        libroRepository.save(libroActualizado);


    }
}
