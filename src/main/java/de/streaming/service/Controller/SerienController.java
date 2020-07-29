package de.streaming.service.Controller;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class SerienController {

    private final SerieRepository repository;

    SerienController(SerieRepository serieRepository) {
        repository = serieRepository;
    }


    @GetMapping(value = "/refresh/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Serie> refreshSerien(@PathVariable Integer id) {
        return repository.findByUserId(id);
    }
}
