package com.prueba.tecnica.app.converter;

import com.prueba.tecnica.app.models.Autor;
import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.service.AutorService;
import com.prueba.tecnica.app.utils.LibroRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LibroRequestDtoToLibroConverter implements Converter<LibroRequestDto, Libro> {

	@Autowired
    private AutorService autorService;

    @Override
    public Libro convert(LibroRequestDto source) {
        Libro target = new Libro();

        Optional<Autor> autorOptional =  autorService.obtenerAutorPorId(source.getAutorId());
        if (autorOptional.isPresent()){
            target.setAutor(autorOptional.get());
        }
        target.setTitulo(source.getTitulo());
        target.setFechaPublicacion(source.getFechaPublicacion());

        return target;


    }
}
