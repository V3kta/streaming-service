package de.streaming.service.Controller;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Repository.SerieRepository;
import de.streaming.service.Repository.UserSerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class SerienController {

    private final UserSerieRepository userSerieRepository;
    private final SerieRepository serieRepository;

    SerienController(UserSerieRepository userSerieRepository, SerieRepository serieRepository) {
        this.userSerieRepository = userSerieRepository;
        this.serieRepository = serieRepository;
    }

    @PostMapping(value = "serie/user/refresh", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserSerie> refreshSerien(@RequestBody User user) {
        return userSerieRepository.findByUserId(user.getId());
    }

    @PostMapping(value = "serie/user/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveSerie(@RequestBody Serie serie) {
        serieRepository.save(serie);
    }

    @PostMapping(value = "serie/user/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSerie(@RequestBody Serie serie) {
        userSerieRepository.deleteBySerie(serie);
    }

    @GetMapping(value = "/serie/refresh/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Serie> refreshAllSerien() {
        return serieRepository.findAll();
    }
}
