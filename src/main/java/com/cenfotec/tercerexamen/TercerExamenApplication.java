package com.cenfotec.tercerexamen;

import com.cenfotec.tercerexamen.entities.Pelicula;
import com.cenfotec.tercerexamen.repositories.PeliculaRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TercerExamenApplication {

    @Bean
    InitializingBean saveDataActores(PeliculaRepository repo){
        List<Pelicula> peliculas = readPeliculasFromCSV("src/main/resources/movies_data.csv");

        return () -> {
            for (Pelicula p : peliculas) {
                repo.save(p);
            }
        };
    }

    private static List<Pelicula> readPeliculasFromCSV(String pathToFile) {
        List<Pelicula> peliculas = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(pathToFile))){

            String line = br.readLine();
            line = br.readLine();

            while (line != null){
                String[] attributes = line.split(",");
                Pelicula pelicula = createPelicula(attributes);
                peliculas.add(pelicula);
                line = br.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return peliculas;
    }

    private static Pelicula createPelicula(String[] metadata) throws Exception{
        String titulo = metadata[0];
        double presupuesto = Double.parseDouble(metadata[1]);
        double duracion = Double.parseDouble(metadata[2]);
        String lenguaje = metadata[3];
        Date fechaLanzamiento = new SimpleDateFormat("yyyy-MM-dd").parse(metadata[4]);
        return new Pelicula(titulo,presupuesto,duracion,lenguaje,fechaLanzamiento);
    }

    public static void main(String[] args) {
        SpringApplication.run(TercerExamenApplication.class, args);
    }

}
