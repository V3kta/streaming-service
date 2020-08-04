package de.streaming.service.Controller;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Repository.UserRepository;
import de.streaming.service.Repository.UserSerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class UserController {

    private final UserSerieRepository userSerieRepository;
    private final UserRepository userRepository;

    UserController(UserSerieRepository userSerieRepository, UserRepository userRepository) {
        this.userSerieRepository = userSerieRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "user/serie/refresh/same", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> refreshSameViewer(@RequestBody Serie serie) {
        List<User> userList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findBySerieId(serie.getId());
        for (UserSerie userSerie : userSerieList) {
            userList.add(userSerie.getUser());
        }
        return userList;
    }
}
