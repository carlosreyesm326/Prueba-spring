package com.prueba.tecnica.app;

import com.prueba.tecnica.app.models.Autor;
import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.repository.LibroRepository;
import com.prueba.tecnica.app.service.LibroService;
import com.prueba.tecnica.app.service.impl.LibroServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppApplicationTests {

	@Mock
	private LibroRepository libroRepository;

	@InjectMocks
	private LibroServiceImpl libroService;

	private Libro libro;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		libro = new Libro();
		libro.setId(new Long(1L));
		libro.setTitulo("Test");
		libro.setAutor(new Autor());
		libro.setFechaPublicacion(1997);
	}

	@Test
	void obtenerTodosLosLibros (){

		when(libroService.obtenerTodosLosLibros()).thenReturn(Arrays.asList(libro));
		assertNotNull(libroService.obtenerTodosLosLibros());
	}

}
