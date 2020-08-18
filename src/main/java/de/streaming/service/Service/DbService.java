package de.streaming.service.Service;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Model.UserDto;
import de.streaming.service.Model.UserSerieKey;
import de.streaming.service.Repository.SerieRepository;
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

    private final Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private String currentToken;


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

        log.info("Refreshed Userserien for User ID: " + userId );
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

    public void saveUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            UserSerieKey userSerieKey = new UserSerieKey(userId, serieId);
            UserSerie userSerie = new UserSerie(userSerieKey, user.get(), serie.get());
            userSerieRepository.save(userSerie);
            log.info("Saved Userserie " + userSerie.getSerie().getName() + " to " + userSerie.getUser().getUsername());
        }
    }

    public void deleteUserSerie(Integer userId, Integer serieId) {
        Optional<Serie> serie = serieRepository.findById(serieId);
        Optional<User> user = userRepository.findById(userId);

        if (serie.isPresent() && user.isPresent()) {
            UserSerieKey userSerieKey = new UserSerieKey(userId, serieId);
            UserSerie userSerie = new UserSerie(userSerieKey, user.get(), serie.get());
            userSerieRepository.delete(userSerie);
            log.info("Deleted Userserie " + userSerie.getSerie().getName() + " from " + userSerie.getUser().getUsername());
        }
    }

    public UserDto validateLogin(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
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
}

