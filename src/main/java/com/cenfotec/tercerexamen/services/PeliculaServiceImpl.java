package com.cenfotec.tercerexamen.services;

import com.cenfotec.tercerexamen.entities.Pelicula;
import com.cenfotec.tercerexamen.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepository;

    //-----------------------------------REST---------------------------------
    @Override
    public Pelicula createPelicula(Pelicula newPelicula) {

        return peliculaRepository.save(newPelicula);

    }

    @Override
    public ResponseEntity<Pelicula> readPelicula(Long id) {

        return peliculaRepository.findById(id).map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());

    }

    @Override
    public ResponseEntity<Pelicula> updatePelicula(Long id, Pelicula pelicula) {

        return peliculaRepository.findById(id).map(item -> {
            item.setTitulo(pelicula.getTitulo());
            item.setPresupuesto(pelicula.getPresupuesto());
            item.setDuracion(pelicula.getDuracion());
            item.setLenguaje(pelicula.getLenguaje());
            item.setFechaLanzamiento(pelicula.getFechaLanzamiento());
            Pelicula updated = peliculaRepository.save(item);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());

    }

    @Override
    public ResponseEntity<?> deletePelicula(Long id) {

        return peliculaRepository.findById(id).map(item -> {
            peliculaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());

    }

    @Override
    public ArrayList<Pelicula> readPeliculas() {
        return (ArrayList<Pelicula>) peliculaRepository.findAll();
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByName(String titulo) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitulo().toLowerCase().contains(titulo.toLowerCase()) || titulo.equalsIgnoreCase("")) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByBudgetRange(double min, double max) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getPresupuesto()>=min && pelicula.getPresupuesto()<=max) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByLengthRange(double min, double max) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getDuracion()>=min && pelicula.getDuracion()<=max) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByDayAndMonth(int day, int month) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();


        for (Pelicula pelicula : peliculas) {
            LocalDate localDate = pelicula.getFechaLanzamiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getDayOfMonth() == day && localDate.getMonthValue() == month) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByDate(String fechaLanzamiento) throws ParseException {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getFechaLanzamiento().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(fechaLanzamiento)) == 0) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByYear(int year) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (Pelicula pelicula : peliculas) {
            calendar.setTime(pelicula.getFechaLanzamiento());
            if (calendar.get(Calendar.YEAR) == year) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    //-------------------------------------GraphQL-----------------------------------
    @Override
    public Pelicula createPeliculaGraphQL(Pelicula newPelicula) {
        return peliculaRepository.save(newPelicula);
    }

    @Override
    public Optional<Pelicula> readPeliculaGraphQL(Long id) {
        return peliculaRepository.findById(id);
    }

    @Override
    public Pelicula updatePeliculaGraphQL(Long id, Pelicula pelicula) {
        Optional<Pelicula> item = peliculaRepository.findById(id);
        item.get().setTitulo(pelicula.getTitulo());
        item.get().setPresupuesto(pelicula.getPresupuesto());
        item.get().setDuracion(pelicula.getDuracion());
        item.get().setLenguaje(pelicula.getLenguaje());
        item.get().setFechaLanzamiento(pelicula.getFechaLanzamiento());
        return peliculaRepository.save(item.get());
    }

    @Override
    public Pelicula deletePeliculaGraphQL(Long id) {
        Optional<Pelicula> item = peliculaRepository.findById(id);
        if(item.isPresent()){
            peliculaRepository.deleteById(id);
        }
        return item.get();
    }

    @Override
    public ArrayList<Pelicula> readPeliculasGraphQL() {
        return (ArrayList<Pelicula>) peliculaRepository.findAll();
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByNameGraphQL(String titulo) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitulo().toLowerCase().contains(titulo.toLowerCase()) || titulo.equalsIgnoreCase("")) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }

    @Override
    public ArrayList<Pelicula> readPeliculasByYearGrapQL(int year) {
        ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) peliculaRepository.findAll();
        ArrayList<Pelicula> listaFiltrada = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (Pelicula pelicula : peliculas) {
            calendar.setTime(pelicula.getFechaLanzamiento());
            if (calendar.get(Calendar.YEAR) == year) {
                listaFiltrada.add(pelicula);
            }
        }
        return listaFiltrada;
    }
}
