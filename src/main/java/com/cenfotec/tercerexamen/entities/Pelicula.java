package com.cenfotec.tercerexamen.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pelicula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "presupuesto", nullable = false)
    private double presupuesto;
    @Column(name = "duracion", nullable = false)
    private double duracion;
    @Column(name = "lenguaje", nullable = false)
    private String lenguaje;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaLanzamiento", nullable = false)
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
