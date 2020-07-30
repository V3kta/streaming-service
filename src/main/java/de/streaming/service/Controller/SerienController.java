package de.streaming.service.Controller;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Repository.SerieRepository;
import de.streaming.service.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class SerienController {

    private final SerieRepository serieRepository;

    SerienController(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @PostMapping(value = "/serie/refresh", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Serie> refreshSerien(@RequestBody User user) {
        return serieRepository.findByUsers(user);
    }
}
