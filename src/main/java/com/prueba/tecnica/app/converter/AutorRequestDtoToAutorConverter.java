package com.prueba.tecnica.app.converter;

import com.prueba.tecnica.app.models.Autor;
import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.service.LibroService;
import com.prueba.tecnica.app.utils.AutorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AutorRequestDtoToAutorConverter implements Converter<AutorRequestDto, Autor> {

   

    @Override
    public Autor convert(AutorRequestDto source) {
        Autor target = new Autor();
        target.setNacionalidad(source.getNacionalidad());
        target.setNombre(source.getNombre());
        target.setFechaNacimiento(source.getFechaNacimiento());



        return target;
    }
}
