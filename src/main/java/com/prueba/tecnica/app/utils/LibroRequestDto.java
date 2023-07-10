package com.prueba.tecnica.app.utils;

import com.prueba.tecnica.app.models.Autor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LibroRequestDto {

    private String titulo;

    private Long autorId;
    private int fechaPublicacion;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(int fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
