package com.cenfotec.tercerexamen.services;

import com.cenfotec.tercerexamen.entities.Pelicula;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

public interface PeliculaService {

    //REST
    Pelicula createPelicula(Pelicula newPelicula);

    ResponseEntity<Pelicula> readPelicula(Long id);

    ResponseEntity<Pelicula> updatePelicula(Long id, Pelicula pelicula);

    ResponseEntity<?> deletePelicula(Long id);

    ArrayList<Pelicula> readPeliculas();

    ArrayList<Pelicula> readPeliculasByName(String titulo);

    ArrayList<Pelicula> readPeliculasByBudgetRange(double min, double max);

    ArrayList<Pelicula> readPeliculasByLengthRange(double min, double max);

    ArrayList<Pelicula> readPeliculasByDayAndMonth(int day, int month);

    ArrayList<Pelicula> readPeliculasByDate(String fechaLanzamiento) throws ParseException;

    ArrayList<Pelicula> readPeliculasByYear(int year);

    //GraphQL
    Pelicula createPeliculaGraphQL(Pelicula newPelicula);

    Optional<Pelicula> readPeliculaGraphQL(Long id);

    Pelicula updatePeliculaGraphQL(Long id, Pelicula pelicula);

    Pelicula deletePeliculaGraphQL(Long id);

    ArrayList<Pelicula> readPeliculasGraphQL();

    ArrayList<Pelicula> readPeliculasByNameGraphQL(String titulo);

    ArrayList<Pelicula> readPeliculasByYearGrapQL(int year);

}
