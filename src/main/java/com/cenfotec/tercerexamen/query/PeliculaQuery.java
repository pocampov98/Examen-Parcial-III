package com.cenfotec.tercerexamen.query;

import com.cenfotec.tercerexamen.entities.Pelicula;
import com.cenfotec.tercerexamen.services.PeliculaService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
public class PeliculaQuery implements GraphQLQueryResolver {

    @Autowired
    PeliculaService peliculaService;

    public Optional<Pelicula> getPelicula(Long id) {
        return this.peliculaService.readPeliculaGraphQL(id);
    }

    public List<Pelicula> getPeliculas() {
        return this.peliculaService.readPeliculas();
    }

    public ArrayList<Pelicula> getPeliculasByName(String titulo) {
        return peliculaService.readPeliculasByNameGraphQL(titulo);
    }

    public ArrayList<Pelicula> getPeliculasByYear(int year) {
        return peliculaService.readPeliculasByYearGrapQL(year);
    }
}
