package de.streaming.service.Service;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Model.UserSerieKey;
import de.streaming.service.Repository.SerieRepository;
import de.streaming.service.Repository.UserRepository;
import de.streaming.service.Repository.UserSerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DbService {

    private final UserSerieRepository userSerieRepository;
    private final UserRepository userRepository;
    private final SerieRepository serieRepository;

    DbService(UserSerieRepository userSerieRepository, UserRepository userRepository, SerieRepository serieRepository) {
        this.userSerieRepository = userSerieRepository;
        this.userRepository = userRepository;
        this.serieRepository = serieRepository;
    }

    public List<Serie> refreshSerien() {

        log.info("Refreshed Serien!");
        return serieRepository.findAll();

    }

    public List<Serie> refreshUserSerien(Integer userId) {

        List<Serie> serienList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findByUserId(userId);
        for (UserSerie userSerie : userSerieList) {
            serienList.add(userSerie.getSerie());
        }

        log.info("Refreshed Userserien!");
        return serienList;
    }

    public List<User> refreshSameViewers(Integer serieId) {
        List<User> userList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findBySerieId(serieId);
        for (UserSerie userSerie : userSerieList) {
            userList.add(userSerie.getUser());
        }

        log.info("Refreshed same Viewers!");
        return userList;
    }

    public void saveUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            UserSerieKey userSerieKey = new UserSerieKey(userId, serieId);
            UserSerie userSerie = new UserSerie(userSerieKey, user.get(), serie.get());
            userSerieRepository.save(userSerie);
            log.info("Saved Userserie!");
        }
    }

    public void deleteUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            UserSerieKey userSerieKey = new UserSerieKey(userId, serieId);
            UserSerie userSerie = new UserSerie(userSerieKey, user.get(), serie.get());
            userSerieRepository.delete(userSerie);
            log.info("Deleted Userserie!");
        }
    }
}
