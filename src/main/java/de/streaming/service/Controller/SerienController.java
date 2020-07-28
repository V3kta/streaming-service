package de.streaming.service.Controller;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Repository.SerieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@Controller
public class SerienController {

    SerieRepository serieRepository;

    @GetMapping(value = "/refresh/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Serie> refreshSerien(@PathVariable Integer userId) {

        return null;
    }
}
