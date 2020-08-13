package de.streaming.service.Controller;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import de.streaming.service.Model.UserSerieIds;
import de.streaming.service.Service.DbService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserSerienController {
    private final DbService dbService;

    UserSerienController(DbService dbService) {
        this.dbService = dbService;
    }

    @GetMapping(value = "/serie/refresh/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Serie> refreshAS(@RequestHeader("Authorization") String token) {
        return dbService.refreshSerien();
    }

    @GetMapping(value = "serie/user/refresh/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Serie> refreshUS(@PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        return dbService.refreshUserSerien(userId);
    }

    @GetMapping(value = "user/serie/refresh/same/{serieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> refreshSV(@PathVariable Integer serieId, @RequestHeader("Authorization") String token) {
        return dbService.refreshSameViewers(serieId);
    }

    @PostMapping(value = "serie/user/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus saveUS(@RequestBody UserSerieIds userSerieIds, @RequestHeader("Authorization") String token) {
        dbService.saveUserSerie(userSerieIds.getUserId(), userSerieIds.getSerieId());
        return HttpStatus.ACCEPTED;
    }

    @PostMapping(value = "serie/user/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUS(@RequestBody UserSerieIds userSerieIds, @RequestHeader("Authorization") String token) {
        dbService.deleteUserSerie(userSerieIds.getUserId(), userSerieIds.getSerieId());
        return  HttpStatus.ACCEPTED;
    }


}
