package de.streaming.service.Service;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.Settings;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Model.SerieDto;
import de.streaming.service.Model.SettingsDto;
import de.streaming.service.Model.UserDto;
import de.streaming.service.Model.UserSerieKey;
import de.streaming.service.Repository.SerieRepository;
import de.streaming.service.Repository.SettingsRepository;
import de.streaming.service.Repository.UserRepository;
import de.streaming.service.Repository.UserSerieRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Getter
@Setter
public class DbService {

    private final UserSerieRepository userSerieRepository;
    private final UserRepository userRepository;
    private final SerieRepository serieRepository;
    private final SettingsRepository settingsRepository;

    private final Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private String currentToken;


    DbService(UserSerieRepository userSerieRepository, UserRepository userRepository, SerieRepository serieRepository, SettingsRepository settingsRepository) {
        this.userSerieRepository = userSerieRepository;
        this.userRepository = userRepository;
        this.serieRepository = serieRepository;
        this.settingsRepository = settingsRepository;
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
            SerieDto serie = new SerieDto(userSerie.getSerie().getId(), userSerie.getSerie().getName(), userSerie.getSerie().getBeschreibung(), userSerie.getSerie().getBildPfad(), userSerie.getZgDatum(), userSerie.getZgFolge(), userSerie.getZgStaffel());
            serienList.add(serie);
        }

        log.info("Refreshed Userserien for User ID: " + userId);
        return serienList;
    }

    public List<User> refreshSameViewers(Integer serieId) {
        List<User> userList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findBySerieId(serieId);
        for (UserSerie userSerie : userSerieList) {
            userList.add(userSerie.getUser());
        }

        log.info("Refreshed same Viewers for Serien ID: " + serieId);
        return userList;
    }

    public void saveUserSerie(UserDto userDto, SerieDto serieDto) {

        Optional<User> user = userRepository.findById(userDto.getId());
        Optional<Serie> serie = serieRepository.findById(serieDto.getId());
        UserSerieKey userSerieKey = new UserSerieKey(userDto.getId(), serieDto.getId());

        if (user.isPresent() && serie.isPresent()) {
            UserSerie userSerie = new UserSerie(userSerieKey, user.get(), serie.get(), serieDto.getZgDatum(), serieDto.getZgFolge(), serieDto.getZgStaffel());
            userSerieRepository.save(userSerie);
            log.info("Saved Userserie " + userSerie.getSerie().getName() + " to " + userSerie.getUser().getUsername());
            return;
        }

        log.error("User oder Serie nicht vorhanden!");

    }

    @Transactional
    public void deleteUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            userSerieRepository.removeBySerieAndUser(serie.get(), user.get());
            log.info("Deleted Userserie " + serie.get().getName() + " from User ID: " + user.get().getId());
        }
    }

    public UserDto validateLogin(String login, String password) {
        User user;

        if (login.contains("@")) {
            user = userRepository.findByEmailAndPassword(login, password);
        } else {
            user = userRepository.findByUsernameAndPassword(login, password);
        }

        if (user != null) {
            String jws = Jwts.builder().setSubject(user.getUsername()).setExpiration(new Date(System.currentTimeMillis() + 600000)).signWith(signKey).compact();
            currentToken = jws;
            return new UserDto(user.getId(), user.getUsername(), user.getVorname(), user.getNachname(), user.getPassword(), jws);
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
        } catch (Exception e) {
            log.warn("Token ungültig - " + e);
            return false;
        }
        return true;
    }

    public SettingsDto refreshSettings(Integer userId) {

        if (settingsRepository.existsByUser_Id(userId)) {
            Settings settings = settingsRepository.findByUser_Id(userId);
            return new SettingsDto(settings.getCardViewMode(), settings.getTheme());
        }

        return new SettingsDto("LIST", "default");

    }

    public void saveSettings(Integer userId, SettingsDto settingsDto) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            log.info("Settings saved!");
            settingsRepository.save(new Settings(user.get(), settingsDto.getCardViewMode(), settingsDto.getTheme()));
        }
    }
}

