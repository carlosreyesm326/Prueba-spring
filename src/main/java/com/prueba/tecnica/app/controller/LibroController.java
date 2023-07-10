package com.prueba.tecnica.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.app.models.Libro;
import com.prueba.tecnica.app.service.LibroService;
import com.prueba.tecnica.app.utils.LibroRequestDto;

@RequestMapping("/libro")
@RestController
public class LibroController {

	@Autowired
	private LibroService libroService;
	
    @GetMapping("/obtenerTodosLosLibros")
    public ResponseEntity<List<Libro>> obtenerTodosLosLibroes ( ){
        return new ResponseEntity<List<Libro>>(libroService.obtenerTodosLosLibros(), HttpStatus.OK);
    }

    @GetMapping("/{libroId}")
    public ResponseEntity<Libro> obtenerLibroesPorId (@PathVariable Long libroId){
        Optional<Libro> LibroOptional = libroService.obtenerLibroPorId(libroId);
        if (LibroOptional.isPresent()){
            return new ResponseEntity<Libro>(LibroOptional.get(), HttpStatus.OK);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{libroId}")
    public ResponseEntity<String> actualizarLibro (@PathVariable Long libroId, @RequestBody LibroRequestDto body){
        try {
            Optional<Libro> LibroOptional = libroService.obtenerLibroPorId(libroId);
            if (LibroOptional.isPresent()){
                libroService.actualizarLibro(body,libroId);
                return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Libro Acttualizado Con Exito");
            }else {
            	 return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El libro no existe");
            }
        	
       

        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el Libro");
        }

    }

    @PostMapping("/")
    public ResponseEntity<String> crearLibro (@RequestBody LibroRequestDto body){
        try {
            libroService.crearLibro(body);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Libro creado Con Exito");

        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el Libro");
        }

    }

    @DeleteMapping("/{libroId}")
    public ResponseEntity<String> EliminarLibro (@PathVariable Long libroId){
        try {
            libroService.eliminarLibro(libroId);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Libro Eliminado Con Exito");

        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Eliminar el Libro");
        }

    }

	
	
}
