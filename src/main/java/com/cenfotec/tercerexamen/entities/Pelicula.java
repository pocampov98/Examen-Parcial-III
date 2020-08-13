package com.cenfotec.tercerexamen.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private double presupuesto;
    private double duracion;
    private String lenguaje;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLanzamiento;

    public Pelicula() {
    }

    public Pelicula(String titulo, double presupuesto, double duracion, String lenguaje, Date fechaLanzamiento) {
        this.titulo = titulo;
        this.presupuesto = presupuesto;
        this.duracion = duracion;
        this.lenguaje = lenguaje;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
