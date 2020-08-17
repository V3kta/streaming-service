package de.streaming.service.Service;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Model.SerieDto;
import de.streaming.service.Model.UserSerieKey;
import de.streaming.service.Repository.SerieRepository;
import de.streaming.service.Repository.UserRepository;
import de.streaming.service.Repository.UserSerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<SerieDto> refreshSerien() {
        List<Serie> serienDb = serieRepository.findAll();
        List<SerieDto> serienList = new ArrayList<>();
        for (Serie serie : serienDb) {
            SerieDto serieDto = new SerieDto(serie.getId(), serie.getName(), serie.getBeschreibung(), serie.getBildPfad(), null, 0, 0);
            serienList.add(serieDto);
        }
        log.info("Refreshed Serien!");
        return serienList;

    }

    public List<SerieDto> refreshUserSerien(Integer userId) {

        List<SerieDto> serienList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findByUserId(userId);

        for (UserSerie userSerie : userSerieList) {
            SerieDto serie = new SerieDto(userSerie.getSerie().getId(),userSerie.getSerie().getName(), userSerie.getSerie().getBeschreibung(),userSerie.getSerie().getBildPfad(), userSerie.getZgDatum(), userSerie.getZgFolge(), userSerie.getZgStaffel());
            serienList.add(serie);
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

    @Transactional
    public void deleteUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            userSerieRepository.removeBySerieAndUser(serie.get(), user.get());
            log.info("Deleted Userserie!");
        }
    }
}
