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

    @GetMapping(value = "/serien", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO.SerieDTO>> getSerien(@RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.getSerien(), HttpStatus.OK);
        }
        log.error("Token invalid - " + token);
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "user/{userId}/serien", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO.SerieDTO>> getUserSerien(@PathVariable Integer userId, @RequestHeader("Authorization") String token, @RequestParam String sorting) {
        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.getUserSerien(userId, sorting), HttpStatus.OK);
        }
        log.error("Token invalid - " + token);
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "serien/{serieId}/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO.UserDTO>> getViewers(@PathVariable Integer serieId, @RequestHeader("Authorization") String token) {

        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.getViewers(serieId), HttpStatus.OK);
        }
        log.warn("Token invalid - " + token);
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "user/{userId}/serien", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus saveUserSerie(@RequestBody DTO.SerieDTO serieDTO, @PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            dbService.saveUserSerie(userId, serieDTO);
            return HttpStatus.OK;
        }
        log.warn("Token invalid - " + token);
        return HttpStatus.FORBIDDEN;
    }

    @DeleteMapping(value = "user/{userId}/serien/{serieId}")
    public HttpStatus deleteUserSerie(@PathVariable Integer userId, @PathVariable Integer serieId, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            dbService.deleteUserSerie(userId, serieId);
            return HttpStatus.OK;
        }
        log.warn("Token invalid - " + token);
        return HttpStatus.FORBIDDEN;
    }


}
