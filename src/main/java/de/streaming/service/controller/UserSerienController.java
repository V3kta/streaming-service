package de.streaming.service.controller;

import de.streaming.service.dto.DTO;
import de.streaming.service.entity.User;
import de.streaming.service.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DTO.SerieDTO>> refreshAS(@RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.refreshSerien(), HttpStatus.ACCEPTED);
        }
        log.warn("Token ungültig - " + token);
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "serie/user/refresh/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO.SerieDTO>> refreshUS(@PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.refreshUserSerien(userId), HttpStatus.ACCEPTED);
        }
        log.warn("Token ungültig - " + token);
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "user/serie/refresh/same/{serieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> refreshSV(@PathVariable Integer serieId, @RequestHeader("Authorization") String token) {

        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.refreshSameViewers(serieId), HttpStatus.ACCEPTED);
        }
        log.warn("Token ungültig - " + token);
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "serie/user/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus saveUS(@RequestBody DTO.UserSerieDTO userSerieDTO, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            dbService.saveUserSerie(userSerieDTO);
            return HttpStatus.ACCEPTED;
        }
        log.warn("Token ungültig - " + token);
        return HttpStatus.UNAUTHORIZED;
    }

    @PostMapping(value = "serie/user/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUS(@RequestBody DTO.UserSerieIdsDTO userSerieIdsDTO, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            dbService.deleteUserSerie(userSerieIdsDTO.getUserId(), userSerieIdsDTO.getSerieId());
            return HttpStatus.ACCEPTED;
        }
        log.warn("Token ungültig - " + token);
        return HttpStatus.UNAUTHORIZED;
    }


}
