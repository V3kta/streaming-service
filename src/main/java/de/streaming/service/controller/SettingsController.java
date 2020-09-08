package de.streaming.service.controller;

import de.streaming.service.dto.SettingsDto;
import de.streaming.service.model.PasswordChange;
import de.streaming.service.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SettingsController {

    private final DbService dbService;

    SettingsController(DbService dbService) {
        this.dbService = dbService;
    }

    @GetMapping(value = "/user/settings/refresh/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SettingsDto> refreshS(@PathVariable Integer userId, @RequestHeader("Authorization") String token ) {
        if (dbService.validateToken(token)) {
            return new ResponseEntity<>(dbService.refreshSettings(userId), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

    }

    @PostMapping(value = "/user/settings/save/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus saveS(@RequestBody SettingsDto settingsDto, @PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            dbService.saveSettings(userId, settingsDto);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.UNAUTHORIZED;
    }

    @PostMapping(value = "/user/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus changePw(@RequestBody PasswordChange passwordChange, @RequestHeader("Authorization") String token) {
        if (dbService.validateToken(token)) {
            if (dbService.changePassword(passwordChange)) {
                return HttpStatus.ACCEPTED;
            };
            return HttpStatus.NOT_ACCEPTABLE;
        }
        return HttpStatus.UNAUTHORIZED;
    }
}
