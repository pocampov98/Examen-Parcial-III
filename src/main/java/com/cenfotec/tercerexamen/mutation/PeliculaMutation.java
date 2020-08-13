package com.cenfotec.tercerexamen.mutation;

import com.cenfotec.tercerexamen.entities.Pelicula;
import com.cenfotec.tercerexamen.services.PeliculaService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class PeliculaMutation  implements GraphQLMutationResolver {

    @Autowired
    PeliculaService peliculaService;

    public Pelicula createPelicula(
            String titulo,
            double presupuesto,
            double duracion,
            String lenguaje,
            String fechaLanzamiento
    ) throws Exception{
        return this.peliculaService.createPeliculaGraphQL(
                new Pelicula(titulo,presupuesto,duracion,lenguaje,new SimpleDateFormat("yyyy-MM-dd").parse(fechaLanzamiento))
        );
    }

    public Pelicula updatePelicula(
            Long id,
            String titulo,
            double presupuesto,
            double duracion,
            String lenguaje,
            String fechaLanzamiento
    ) throws Exception{
        return this.peliculaService.updatePeliculaGraphQL(
                id,
                new Pelicula(titulo,presupuesto,duracion,lenguaje,new SimpleDateFormat("yyyy-MM-dd").parse(fechaLanzamiento))
        );
    }

    public Pelicula deletePelicula(
            Long id
    ){
        return this.peliculaService.deletePeliculaGraphQL(id);
    }

}
