package com.prueba.tecnica.app.service.impl;

import com.prueba.tecnica.app.converter.AutorRequestDtoToAutorConverter;
import com.prueba.tecnica.app.models.Autor;
import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.repository.AutorRepository;
import com.prueba.tecnica.app.service.AutorService;
import com.prueba.tecnica.app.utils.AutorRequestDto;

import antlr.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRequestDtoToAutorConverter autorRequestDtoToAutorConverter;
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public List<Autor> obtenerTodosLosAutores() {
		return autorRepository.findAll();
	}

	@Override
	public Optional<Autor> obtenerAutorPorId(Long id) {
		return autorRepository.findById(id);
	}

	@Override
	public void crearAutor(AutorRequestDto autorRequesDto) {
		autorRepository.save(autorRequestDtoToAutorConverter.convert(autorRequesDto));
	}

	@Override
	public void eliminarAutor(Long idAutor) {
		autorRepository.deleteById(idAutor);
	}




}
