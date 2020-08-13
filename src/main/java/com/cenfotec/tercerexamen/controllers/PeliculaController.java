package com.cenfotec.tercerexamen.controllers;

import com.cenfotec.tercerexamen.entities.Pelicula;
import com.cenfotec.tercerexamen.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping({"/peliculas"})
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Pelicula> readPelicula(@PathVariable Long id) {
        return peliculaService.readPelicula(id);
    }

    @GetMapping
    public ArrayList<Pelicula> readPeliculas (
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double minBudget, @RequestParam(required = false) Double maxBudget,
            @RequestParam(required = false) Double minLength, @RequestParam(required = false) Double maxLength,
            @RequestParam(required = false) Integer day, @RequestParam(required = false) Integer month,
            @RequestParam(required = false) String fechaLanzamiento,
            @RequestParam(required = false) Integer year
    ) throws Exception{
            if(titulo != null)
                return peliculaService.readPeliculasByName(titulo);
            if(minBudget != null && maxBudget != null)
                return peliculaService.readPeliculasByBudgetRange(minBudget,maxBudget);
            if(minLength != null && maxLength != null)
                return peliculaService.readPeliculasByLengthRange(minLength,maxLength);
            if(day != null && month != null)
                return peliculaService.readPeliculasByDayAndMonth(day,month);
            if(fechaLanzamiento != null)
                return peliculaService.readPeliculasByDate(fechaLanzamiento);
            if(year != null)
                return peliculaService.readPeliculasByYear(year);
            return peliculaService.readPeliculas();
    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.createPelicula(pelicula);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return peliculaService.updatePelicula(id, pelicula);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deletePelicula(@PathVariable("id") Long id) {
        return peliculaService.deletePelicula(id);
    }
}
