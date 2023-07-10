package com.prueba.tecnica.app.controller;

import com.prueba.tecnica.app.models.Autor;
import com.prueba.tecnica.app.service.AutorService;
import com.prueba.tecnica.app.utils.AutorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/autor")
@RestController
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/obtenerTodosLosAutores")
    public ResponseEntity<List<Autor>> obtenerTodosLosAutores ( ){
        return new ResponseEntity<List<Autor>>(autorService.obtenerTodosLosAutores(), HttpStatus.OK);
    }

    @GetMapping("/{autorId}")
    public ResponseEntity<Autor> obtenerAutoresPorId (@PathVariable Long autorId){
        Optional<Autor> autorOptional = autorService.obtenerAutorPorId(autorId);
        if (autorOptional.isPresent()){
            return new ResponseEntity<Autor>(autorOptional.get(), HttpStatus.OK);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/")
    public ResponseEntity<String> crearAutor (@RequestBody AutorRequestDto body){
        try {
            autorService.crearAutor(body);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Autor creado Con Exito");

        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el Autor");
        }

    }

    @DeleteMapping("/{autorId}")
    public ResponseEntity<String> EliminarAutor (@PathVariable Long autorId){
        try {
            autorService.eliminarAutor(autorId);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Autor Eliminado Con Exito");

        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Eliminar el Autor");
        }

    }

}
