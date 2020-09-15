package de.streaming.service.service;

import de.streaming.service.dto.DTO;
import de.streaming.service.entity.Serie;
import de.streaming.service.entity.Settings;
import de.streaming.service.entity.User;
import de.streaming.service.entity.UserSerie;
import de.streaming.service.model.UserSerieKey;
import de.streaming.service.repository.SerieRepository;
import de.streaming.service.repository.SettingsRepository;
import de.streaming.service.repository.UserRepository;
import de.streaming.service.repository.UserSerieRepository;
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

    public List<DTO.SerieDTO> getSerien() {
        List<Serie> serienDb = serieRepository.findAll();
        List<DTO.SerieDTO> serienList = new ArrayList<>();
        for (Serie serie : serienDb) {
            DTO.SerieDTO serieDto = new DTO.SerieDTO(serie.getId(), serie.getName(), serie.getBeschreibung(), serie.getBildPfad(), null, 0, 0);
            serienList.add(serieDto);
        }
        log.info("Refreshing Serien");
        return serienList;

    }

    public List<DTO.SerieDTO> getUserSerien(Integer userId, String sorting) {
        List<DTO.SerieDTO> serienList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findByUserId(userId);

        for (UserSerie userSerie : userSerieList) {
            DTO.SerieDTO serie = new DTO.SerieDTO(userSerie.getSerie().getId(), userSerie.getSerie().getName(), userSerie.getSerie().getBeschreibung(), userSerie.getSerie().getBildPfad(), userSerie.getZgDatum(), userSerie.getZgFolge(), userSerie.getZgStaffel());
            serienList.add(serie);
        }

        switch (sorting) {
            case "nameasc":
                serienList.sort(DTO.SerieDTO.SerieNameAscComp);
                return serienList;
            case "namedesc":
                serienList.sort(DTO.SerieDTO.SerieNameDescComp);
                return serienList;
            case "dateasc":
                serienList.sort(DTO.SerieDTO.SerieDateAscComp);
                return serienList;
            case "datedesc":
                serienList.sort(DTO.SerieDTO.SerieDateDescComp);
                return serienList;
            default:
                break;
        }

        log.info("Refreshed Userserien for User ID " + userId);
        return serienList;
    }

    public List<DTO.UserDTO> getViewers(Integer serieId) {
        List<DTO.UserDTO> userList = new ArrayList<>();
        List<UserSerie> userSerieList = userSerieRepository.findBySerieId(serieId);
        for (UserSerie userSerie : userSerieList) {
            User user = userSerie.getUser();
            userList.add(new DTO.UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getVorname(), user.getNachname(), ""));
        }

        log.info("Refreshing Viewers for Serien ID " + serieId);
        return userList;
    }

    public void saveUserSerie(Integer userId, DTO.SerieDTO serieDTO) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Serie> serie = serieRepository.findById(serieDTO.getId());
        UserSerieKey userSerieKey = new UserSerieKey(userId, serieDTO.getId());

        if (user.isPresent() && serie.isPresent()) {
            UserSerie userSerie = new UserSerie(userSerieKey, user.get(), serie.get(),
                    serieDTO.getZgDatum(),
                    serieDTO.getZgFolge(),
                    serieDTO.getZgStaffel());
            userSerieRepository.save(userSerie);
            log.info("Saving Userserie " + userSerie.getSerie().getName() + " to User " + userSerie.getUser().getUsername());
            return;
        }

        log.error("User or Serie not found!");

    }

    @Transactional
    public void deleteUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            userSerieRepository.removeBySerieAndUser(serie.get(), user.get());
            log.info("Deleting Userserie " + serie.get().getName() + " from User ID " + user.get().getId());
        }
    }

    public DTO.UserDTO validateLogin(String login, String password) {
        User user;

        if (login.contains("@")) {
            user = userRepository.findByEmailAndPassword(login, password);
        } else {
            user = userRepository.findByUsernameAndPassword(login, password);
        }

        if (user != null) {
            String jws = Jwts.builder().setSubject(user.getUsername()).setExpiration(new Date(System.currentTimeMillis() + 900000)).signWith(signKey).compact();
            currentToken = jws;
            return new DTO.UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getVorname(), user.getNachname(), jws);
        }

        return null;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
        } catch (Exception e) {
            log.warn("Token ungültig - " + e);
            return false;
        }
        return true;
    }

    public DTO.SettingsDTO refreshSettings(Integer userId) {

        if (settingsRepository.existsByUser_Id(userId)) {
            Settings settings = settingsRepository.findByUser_Id(userId);
            return new DTO.SettingsDTO(settings.getCardViewMode(), settings.getTheme());
        }

        return new DTO.SettingsDTO("LIST", "default");

    }

    public void saveSettings(Integer userId, DTO.SettingsDTO settingsDTO) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            log.info("Settings saved!");
            settingsRepository.save(new Settings(user.get(), settingsDTO.getCardViewMode(), settingsDTO.getTheme()));
        }
    }

    public Boolean changeUserDetail(DTO.PasswordInfoDTO passwordInfoDTO) {
        if (userRepository.existsById(passwordInfoDTO.getId()) && userRepository.existsByPassword(passwordInfoDTO.getOldPassword())) {
            Optional<User> user = userRepository.findById(passwordInfoDTO.getId());
            if (user.isPresent()) {
                user.get().setPassword(passwordInfoDTO.getNewPassword());
                userRepository.save(user.get());
                return true;
            }
        }
        return false;
    }

    public Boolean changeUserDetail(DTO.UsernameInfoDTO usernameInfoDTO) {
        if (userRepository.existsById(usernameInfoDTO.getId()) && !userRepository.existsByUsername(usernameInfoDTO.getUsername())) {
            Optional<User> user = userRepository.findById(usernameInfoDTO.getId());
            if (user.isPresent()) {
                user.get().setUsername(usernameInfoDTO.getUsername());
                userRepository.save(user.get());
                return true;
            }
            return false;
        }
        return false;
    }

    public Boolean changeUserDetail(DTO.EmailInfoDTO emailInfoDTO) {
        if (userRepository.existsById(emailInfoDTO.getId()) && !userRepository.existsByEmail(emailInfoDTO.getEmail())) {
            Optional<User> user = userRepository.findById(emailInfoDTO.getId());
            if (user.isPresent()) {
                user.get().setEmail(emailInfoDTO.getEmail());
                userRepository.save(user.get());
                return true;
            }
            return false;
        }
        return false;
    }
}

